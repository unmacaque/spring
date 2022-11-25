package com.gmail.unmacaque.spring.resilience4j.web;

import com.gmail.unmacaque.spring.resilience4j.domain.CounterService;
import com.gmail.unmacaque.spring.resilience4j.util.FailureException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import io.github.resilience4j.reactor.ratelimiter.operator.RateLimiterOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class CircuitBreakerController {

	@Autowired
	private CircuitBreakerRegistry circuitBreakerRegistry;

	@Autowired
	private RateLimiterRegistry rateLimiterRegistry;

	@Autowired
	private CounterService counterService;

	@GetMapping({"/", "breaker"})
	public Mono<String> breaker() {
		return Mono
				.fromCallable(counterService::retrieveIntegerOrThrow)
				.transformDeferred(CircuitBreakerOperator.of(circuitBreakerRegistry.circuitBreaker("myBreaker")))
				.map(Object::toString)
				.onErrorReturn(FailureException.class, "A failure has occured")
				.onErrorReturn(CallNotPermittedException.class,
						"We are encountering technical difficulties. Please try again after some time.");
	}

	@GetMapping("/ratelimiter")
	public Mono<String> ratelimiter() {
		return Mono
				.fromCallable(counterService::retrieveInteger)
				.transform(RateLimiterOperator.of(rateLimiterRegistry.rateLimiter("myLimiter")))
				.map(Object::toString)
				.onErrorReturn(RequestNotPermitted.class,
						"We are currently experiencing heavy load. Please try again after some time.");
	}

}
