package com.gmail.unmacaque.spring.webflux;

import com.gmail.unmacaque.spring.domain.Greeting;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

@WebFluxTest(WebFluxController.class)
class WebFluxControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testHello() {
		webTestClient
				.get()
				.uri("/hello")
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("name").isEqualTo("Hello World")
				.jsonPath("time").exists();
	}

	@Test
	void testFlux() {
		final Supplier<? extends Publisher<? extends Greeting>> scenarioSupplier = () -> webTestClient
				.get()
				.uri("/flux")
				.accept(MediaType.APPLICATION_NDJSON)
				.exchange()
				.expectStatus().isOk()
				.returnResult(Greeting.class).getResponseBody();

		StepVerifier.withVirtualTime(scenarioSupplier)
				.expectSubscription()
				.thenAwait(Duration.ofSeconds(1))
				.assertNext(this::assertGreeting)
				.thenAwait(Duration.ofSeconds(9))
				.expectNextCount(9)
				.verifyComplete();
	}

	private void assertGreeting(Greeting greeting) {
		assertThat(greeting.name()).isEqualTo("Hello World");
		assertThat(greeting.time()).isNotNull();
	}
}
