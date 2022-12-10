package com.gmail.unmacaque.spring.testcontainers.web;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
public class WebController {

	private final RestTemplate restTemplate;

	public WebController(RestTemplateBuilder restTemplateBuilder) {
		restTemplate = restTemplateBuilder.build();
	}

	@GetMapping("/")
	public String getGreeting() {
		return restTemplate.getForObject("/", String.class);
	}

	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<?> handleClientErrorException(HttpClientErrorException exception) {
		return ResponseEntity.internalServerError().body(exception.getStatusText());
	}
}
