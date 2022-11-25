package com.gmail.unmacaque.spring.webflux.functional.web;

import com.gmail.unmacaque.spring.webflux.functional.domain.Greeting;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.Instant;

public class RouteHandler {
	public Mono<ServerResponse> handleRoot(ServerRequest request) {
		final var greeting = new Greeting("Hello World", Instant.now());
		return ServerResponse
				.ok()
				.body(Mono.just(greeting), Greeting.class);
	}

	public Mono<ServerResponse> handleHelloName(ServerRequest request) {
		final var greeting = new Greeting("Hello " + request.pathVariable("name"), Instant.now());
		return ServerResponse
				.ok()
				.body(Mono.just(greeting), Greeting.class);
	}
}
