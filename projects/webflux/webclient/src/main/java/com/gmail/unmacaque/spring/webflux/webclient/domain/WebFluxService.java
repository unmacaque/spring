package com.gmail.unmacaque.spring.webflux.webclient.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WebFluxService {

	private static final Logger log = LoggerFactory.getLogger(WebFluxService.class);

	private final String baseUrl;

	public WebFluxService(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public Mono<String> doCall() {
		return WebClient
				.create(baseUrl)
				.get()
				.retrieve()
				.onStatus(HttpStatusCode::is4xxClientError, response -> Mono.error(new ClientErrorException(response)))
				.onStatus(HttpStatusCode::is5xxServerError, response -> Mono.error(new ServerErrorException(response)))
				.bodyToMono(String.class)
				.doOnNext(log::info);
	}

}
