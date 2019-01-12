package com.gmail.unmacaque.spring.webflux;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WebFluxService {

	private static final Logger log = LoggerFactory.getLogger(WebFluxService.class);

	public void doCall() {
		WebClient
				.create("http://localhost:8888")
				.get()
				.exchange()
				.blockOptional()
				.orElseThrow(IllegalStateException::new)
				.toEntity(String.class)
				.map(ResponseEntity::getBody)
				.subscribe(log::info);
	}

}
