package com.gmail.unmacaque.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebTestClient
public class ApplicationTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	public void testRoot() {
		// @formatter:off
		webTestClient.get()
				.uri("/")
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("name").isEqualTo("Hello World")
				.jsonPath("time").exists();
		// @formatter:on
	}

	@Test
	public void testHelloName() {
		// @formatter:off
		webTestClient.get()
				.uri("/hello/John")
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("name").isEqualTo("Hello John")
				.jsonPath("time").exists();
		// @formatter:on
	}
}
