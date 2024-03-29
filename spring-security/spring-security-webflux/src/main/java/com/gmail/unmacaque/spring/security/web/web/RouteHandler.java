package com.gmail.unmacaque.spring.security.web.web;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class RouteHandler {
	public Mono<ServerResponse> handleRoot(@SuppressWarnings("unused") ServerRequest request) {
		return ServerResponse
				.ok()
				.body(Mono.just("Hello World"), String.class);
	}

	public Mono<ServerResponse> handleHelloName(ServerRequest request) {
		return ServerResponse
				.ok()
				.body(Mono.just("Hello " + request.pathVariable("name")), String.class);
	}
}
