package com.gmail.unmacaque.spring.webflux.webclient.domain;

import org.springframework.web.reactive.function.client.ClientResponse;

public class ServerErrorException extends RuntimeException {

	private final transient ClientResponse response;

	public ServerErrorException(ClientResponse response) {
		this.response = response;
	}

	public ClientResponse getResponse() {
		return response;
	}
}
