package com.gmail.unmacaque.spring.resilience4j;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
class ApplicationTest {

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

	@Test
	void testBulkhead() {
		webClient
				.get()
				.uri("/bulkhead")
				.exchange()
				.expectStatus().isOk();
	}

	@Test
	void testRatelimiter() {
		webClient
				.get()
				.uri("/ratelimiter")
				.exchange()
				.expectStatus().isOk();
	}

	@Test
	void testRetry() {
		webClient
				.get()
				.uri("/retry")
				.exchange()
				.expectStatus().isEqualTo(HttpStatus.SERVICE_UNAVAILABLE);
	}
}
