package com.gmail.unmacaque.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity;

@SpringBootTest
class ApplicationTest {

	@Autowired
	private ApplicationContext applicationContext;

	private WebTestClient webTestClient;

	@BeforeEach
	void setup() {
		webTestClient = WebTestClient
				.bindToApplicationContext(applicationContext)
				.apply(springSecurity())
				.configureClient()
				.build();
	}

	@Test
	void testRoot() {
		webTestClient
				.get()
				.uri("/")
				.exchange()
				.expectStatus()
				.isOk();
	}

	@Test
	@WithMockUser
	void testHelloName() {
		webTestClient
				.get()
				.uri("/hello/John")
				.exchange()
				.expectStatus()
				.isOk();
	}

	@Test
	void testHelloNameWithoutAuthenticationIsUnauthorized() {
		webTestClient
				.get()
				.uri("/hello/John")
				.exchange()
				.expectStatus()
				.isUnauthorized();
	}

}
