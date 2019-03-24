package com.gmail.unmacaque.spring.webflux;

import com.gmail.unmacaque.spring.domain.Greeting;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class RouteHandler {
	public Mono<ServerResponse> handleRoot(ServerRequest request) {
		var greeting = new Greeting("Hello World", LocalDateTime.now());
		return ServerResponse
				.ok()
				.body(Mono.just(greeting), Greeting.class);
	}

	public Mono<ServerResponse> handleHelloName(ServerRequest request) {
		var greeting = new Greeting("Hello " + request.pathVariable("name"), LocalDateTime.now());
		return ServerResponse
				.ok()
				.body(Mono.just(greeting), Greeting.class);
	}
}
