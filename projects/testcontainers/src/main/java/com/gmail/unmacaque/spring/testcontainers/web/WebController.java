package com.gmail.unmacaque.spring.testcontainers.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@RestController
public class WebController {

	private final RestClient restClient;

	public WebController(RestClient.Builder builder) {
		this.restClient = builder.build();
	}

	@GetMapping("/")
	public String getGreeting() {
		return restClient.get().uri("/").retrieve().body(String.class);
	}

	@ExceptionHandler(RestClientException.class)
	public ResponseEntity<?> handleClientErrorException(RestClientException exception) {
		return ResponseEntity.internalServerError().body("Oh no!");
	}
}
