package com.gmail.unmacaque.spring.webflux.annotated.web;

import com.gmail.unmacaque.spring.webflux.annotated.domain.Greeting;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;

@RestController
public class WebFluxController {

	@GetMapping("/hello")
	public Mono<Greeting> hello() {
		return Mono.just(new Greeting("Hello World", Instant.now()));
	}

	@GetMapping("/flux")
	public Flux<Greeting> flux() {
		return Flux
				.<Greeting>generate(s -> s.next(new Greeting("Hello World", Instant.now())))
				.take(10);
	}

	@GetMapping("/events")
	public Flux<ServerSentEvent<Greeting>> events() {
		return Flux
				.interval(Duration.ZERO, Duration.ofSeconds(1))
				.map(aLong -> ServerSentEvent.<Greeting>builder()
						.id(String.valueOf(aLong))
						.event("message")
						.data(new Greeting("Hello World", Instant.now()))
						.build());
	}
}
