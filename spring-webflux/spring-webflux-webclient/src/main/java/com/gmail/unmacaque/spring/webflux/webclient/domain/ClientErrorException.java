package com.gmail.unmacaque.spring.webflux.webclient.domain;

import org.springframework.web.reactive.function.client.ClientResponse;

public class ClientErrorException extends RuntimeException {

	private final ClientResponse response;

	public ClientErrorException(ClientResponse response) {
		this.response = response;
	}

	public ClientResponse getResponse() {
		return response;
	}
}
