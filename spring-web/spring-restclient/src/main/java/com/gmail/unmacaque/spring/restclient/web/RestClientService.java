package com.gmail.unmacaque.spring.restclient.web;

import com.gmail.unmacaque.spring.restclient.domain.Bundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class RestClientService {

	private static final Logger logger = LoggerFactory.getLogger(RestClientService.class);

	private final RestClient restClient;

	public RestClientService(RestClient.Builder restClientBuilder) {
		this.restClient = restClientBuilder.build();
	}

	public String doCall() {
		final var bundle = restClient
				.get()
				.uri("http://localhost:8888/")
				.retrieve()
				.body(Bundle.class);
		if (bundle == null) {
			return null;
		}
		logger.info(bundle.content());
		return bundle.content();
	}
}
