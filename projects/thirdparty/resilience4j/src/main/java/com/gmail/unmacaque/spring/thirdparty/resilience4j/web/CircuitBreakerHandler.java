package com.gmail.unmacaque.spring.thirdparty.resilience4j.web;

import com.gmail.unmacaque.spring.thirdparty.resilience4j.domain.CounterService;
import com.gmail.unmacaque.spring.thirdparty.resilience4j.util.FailureException;
import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.reactor.bulkhead.operator.BulkheadOperator;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import io.github.resilience4j.reactor.ratelimiter.operator.RateLimiterOperator;
import io.github.resilience4j.reactor.retry.RetryOperator;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static org.springframework.http.HttpStatus.*;

@Component
public class CircuitBreakerHandler {

	@Autowired
	private BulkheadRegistry bulkheadRegistry;

	@Autowired
	private CircuitBreakerRegistry circuitBreakerRegistry;

	@Autowired
	private RateLimiterRegistry rateLimiterRegistry;

	@Autowired
	private RetryRegistry retryRegistry;

	@Autowired
	private CounterService counterService;

	public Mono<ServerResponse> breaker(ServerRequest ignored) {
		return Mono
				.fromCallable(counterService::retrieveIntegerOrThrow)
				.transformDeferred(CircuitBreakerOperator.of(circuitBreakerRegistry.circuitBreaker("myBreaker")))
				.flatMap(ServerResponse.ok()::bodyValue)
				.onErrorResume(FailureException.class, e -> ServerResponse.status(INTERNAL_SERVER_ERROR)
						.bodyValue("A failure has occured")
				)
				.onErrorResume(CallNotPermittedException.class, e -> ServerResponse.status(SERVICE_UNAVAILABLE)
						.bodyValue("We are encountering technical difficulties. Please try again after some time.")
				);
	}

	public Mono<ServerResponse> bulkhead(ServerRequest ignored) {
		return Mono
				.fromSupplier(counterService::retrieveInteger)
				.delayElement(Duration.ofSeconds(1))
				.transform(BulkheadOperator.of(bulkheadRegistry.bulkhead("myBulkhead")))
				.flatMap(ServerResponse.ok()::bodyValue)
				.onErrorResume(BulkheadFullException.class, e -> ServerResponse.status(SERVICE_UNAVAILABLE)
						.bodyValue("Too many concurrent calls. Please try again after some time.")
				);
	}

	public Mono<ServerResponse> ratelimiter(ServerRequest ignored) {
		return Mono
				.fromCallable(counterService::retrieveInteger)
				.transform(RateLimiterOperator.of(rateLimiterRegistry.rateLimiter("myLimiter")))
				.flatMap(ServerResponse.ok()::bodyValue)
				.onErrorResume(RequestNotPermitted.class, e -> ServerResponse.status(TOO_MANY_REQUESTS)
						.bodyValue("We are currently experiencing heavy load. Please try again after some time.")
				);
	}

	public Mono<ServerResponse> retry(ServerRequest ignored) {
		return Mono
				.fromCallable(counterService::retrieveIntegerAlwaysThrow)
				.transformDeferred(RetryOperator.of(retryRegistry.retry("myRetry")))
				.flatMap(ServerResponse.ok()::bodyValue)
				.onErrorResume(FailureException.class, e -> ServerResponse.status(SERVICE_UNAVAILABLE)
						.bodyValue("Operation failed. Please try again after some time.")
				);
	}

}
