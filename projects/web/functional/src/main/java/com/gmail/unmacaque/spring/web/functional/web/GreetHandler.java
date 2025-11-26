package com.gmail.unmacaque.spring.web.functional.web;

import com.gmail.unmacaque.spring.web.functional.domain.Greeting;
import org.jspecify.annotations.NonNull;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.time.LocalDateTime;

public class GreetHandler {
	@NonNull
	public ServerResponse greetAnonymous(@SuppressWarnings("unused") ServerRequest request) {
		return ServerResponse
				.ok()
				.body(new Greeting("Hello World", LocalDateTime.now()));
	}

	@NonNull
	public ServerResponse greetWithName(ServerRequest request) {
		return ServerResponse
				.accepted()
				.body(new Greeting(request.pathVariable("name"), LocalDateTime.now()));
	}
}
