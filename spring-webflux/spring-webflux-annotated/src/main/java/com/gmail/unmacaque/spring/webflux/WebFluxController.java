package com.gmail.unmacaque.spring.webflux;

import com.gmail.unmacaque.spring.domain.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@RestController
public class WebFluxController {

	@GetMapping("/hello")
	public Mono<Greeting> hello() {
		return Mono.just(new Greeting("Hello World", Instant.now()));
	}

	@GetMapping("/flux")
	public Flux<Greeting> helloFlux() {
		return Flux
				.<Greeting>generate(s -> s.next(new Greeting("Hello World", Instant.now())))
				.take(10);
	}
}
