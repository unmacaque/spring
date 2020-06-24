package com.gmail.unmacaque.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
class ApplicationTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testRoot() {
		webTestClient
				.get()
				.uri("/")
				.exchange()
				.expectStatus().isOk()
				.expectBody(String.class).isEqualTo("Hello World");
	}

	@Test
	@WithMockUser
	void testHelloName() {
		webTestClient
				.get()
				.uri("/hello/John")
				.exchange()
				.expectStatus().isOk()
				.expectBody(String.class).isEqualTo("Hello John");
	}

	@Test
	void testHelloNameWithoutAuthenticationIsUnauthorized() {
		webTestClient
				.get()
				.uri("/hello/John")
				.exchange()
				.expectStatus().isUnauthorized();
	}

}
