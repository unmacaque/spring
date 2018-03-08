package com.gmail.unmacaque.spring.webflux;

import java.time.LocalDateTime;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.gmail.unmacaque.spring.domain.Greeting;

import reactor.core.publisher.Mono;

public class RouteHandler {
	public Mono<ServerResponse> handleRoot(ServerRequest request) {
		Greeting greeting = new Greeting("Hello World", LocalDateTime.now());
		return ServerResponse
				.ok()
				.body(Mono.just(greeting), Greeting.class);
	}

	public Mono<ServerResponse> handleHelloName(ServerRequest request) {
		Greeting greeting = new Greeting("Hello " + request.pathVariable("name"), LocalDateTime.now());
		return ServerResponse
				.ok()
				.body(Mono.just(greeting), Greeting.class);
	}
}
