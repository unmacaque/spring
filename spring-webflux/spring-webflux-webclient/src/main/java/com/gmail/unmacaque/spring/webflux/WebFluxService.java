package com.gmail.unmacaque.spring.webflux;

import com.gmail.unmacaque.spring.domain.ClientErrorException;
import com.gmail.unmacaque.spring.domain.ServerErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WebFluxService {

	private static final Logger log = LoggerFactory.getLogger(WebFluxService.class);

	public Mono<String> doCall() {
		return WebClient
				.create("http://localhost:8888")
				.get()
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new ClientErrorException(response)))
				.onStatus(HttpStatus::is5xxServerError, response -> Mono.error(new ServerErrorException(response)))
				.bodyToMono(String.class)
				.doOnNext(log::info);
	}

}
