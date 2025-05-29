package com.gmail.unmacaque.spring.security.oauth2.client.web;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import static org.springframework.security.oauth2.client.web.client.RequestAttributeClientRegistrationIdResolver.clientRegistrationId;

@Service
public class ResourceService {

	private final RestClient restClient;

	public ResourceService(RestClient.Builder restClientBuilder) {
		this.restClient = restClientBuilder.build();
	}

	public String retrieveResource() {
		return restClient.get()
				.attributes(clientRegistrationId("authorization-server"))
				.retrieve()
				.body(String.class);
	}

}
