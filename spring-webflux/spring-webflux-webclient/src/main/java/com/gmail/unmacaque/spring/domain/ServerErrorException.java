package com.gmail.unmacaque.spring.domain;

import org.springframework.web.reactive.function.client.ClientResponse;

public class ServerErrorException extends RuntimeException {

	private final ClientResponse response;

	public ServerErrorException(ClientResponse response) {
		this.response = response;
	}

	public ClientResponse getResponse() {
		return response;
	}
}
