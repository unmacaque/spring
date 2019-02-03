package com.gmail.unmacaque.spring.web;

import com.gmail.unmacaque.spring.domain.IntegerService;
import com.gmail.unmacaque.spring.util.FailureException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerOpenException;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class CircuitBreakerController {

	private final CircuitBreaker circuitBreaker;

	private final IntegerService integerService;

	public CircuitBreakerController(CircuitBreaker circuitBreaker, IntegerService integerService) {
		this.circuitBreaker = circuitBreaker;
		this.integerService = integerService;
	}

	@GetMapping({"/", "breaker"})
	public Mono<String> breaker() {
		return Mono
				.fromCallable(integerService::retrieveInteger)
				.transform(CircuitBreakerOperator.of(circuitBreaker))
				.map(Object::toString)
				.onErrorReturn(FailureException.class, "A failure has occured")
				.onErrorReturn(CircuitBreakerOpenException.class,
						"We are encountering technical difficulties. Please try again after some time.");
	}

}
