package com.gmail.unmacaque.spring.rest;

import java.time.LocalDateTime;

public class GreetResponse {

	private final LocalDateTime date = LocalDateTime.now();
	private final String response;

	public GreetResponse() {
		this("anonymous");
	}

	public GreetResponse(String response) {
		this.response = response;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public String getResponse() {
		return response;
	}

	@Override
	public String toString() {
		return response;
	}
}
