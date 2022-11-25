package com.gmail.unmacaque.spring.security.oauth2.webflux.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockOidcLogin;

@SpringBootTest
@AutoConfigureWebTestClient
class WebControllerTest {

	@Autowired
	private WebTestClient client;

	@Test
	void testIndex() throws Exception {
		client
				.mutateWith(mockOidcLogin())
				.get()
				.uri("/")
				.exchange()
				.expectStatus().isOk()
				.expectBody(String.class).isEqualTo("user");
	}

}
