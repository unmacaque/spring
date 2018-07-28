package com.gmail.unmacaque.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientApplicationRunner implements ApplicationRunner {

	private static final Logger log = LoggerFactory.getLogger(WebClientApplicationRunner.class);

	@Override
	public void run(ApplicationArguments args) throws Exception {
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
