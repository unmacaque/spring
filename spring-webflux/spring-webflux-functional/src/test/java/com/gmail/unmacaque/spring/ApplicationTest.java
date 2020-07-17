package com.gmail.unmacaque.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
class ApplicationTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testRoot() {
		webTestClient.get()
				.uri("/")
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("name").isEqualTo("Hello World")
				.jsonPath("time").exists();
	}

	@Test
	void testHelloName() {
		webTestClient.get()
				.uri("/hello/John")
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("name").isEqualTo("Hello John")
				.jsonPath("time").exists();
	}
}
