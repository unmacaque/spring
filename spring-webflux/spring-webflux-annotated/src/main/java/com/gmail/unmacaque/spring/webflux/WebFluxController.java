package com.gmail.unmacaque.spring.webflux;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.unmacaque.spring.domain.Greeting;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class WebFluxController {

	@GetMapping("/hello")
	public Mono<Greeting> hello() {
		return Mono.just(new Greeting("Hello World", LocalDateTime.now()));
	}

	@GetMapping("/flux")
	public Flux<Greeting> helloFlux() {
		return Flux
				.<Greeting>generate(s -> s.next(new Greeting("Hello World", LocalDateTime.now())))
				.delayElements(Duration.ofSeconds(1))
				.take(10);
	}
}
