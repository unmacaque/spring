package com.gmail.unmacaque.spring.web;

import org.springframework.lang.NonNull;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

public class GreetHandler {
	@NonNull
	public ServerResponse greetAnonymous(ServerRequest request) {
		return ServerResponse.ok().body(new Greeting());
	}

	@NonNull
	public ServerResponse greetWithName(ServerRequest request) {
		return ServerResponse.accepted().body(new Greeting(request.pathVariable("name")));
	}
}
