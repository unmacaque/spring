package com.gmail.unmacaque.spring.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
class CircuitBreakerControllerTest {

	@Autowired
	private WebTestClient webClient;

	@Test
	void testBreaker() {
		webClient
				.get()
				.uri("/breaker")
				.exchange()
				.expectStatus().isOk();
	}
}
