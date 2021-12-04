package com.gmail.unmacaque.spring.webflux;

import com.gmail.unmacaque.spring.domain.Message;
import com.gmail.unmacaque.spring.domain.MessageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebFluxTest
class MessageRestControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private MessageRepository messageRepository;

	@Test
	void testGetMessages() {
		when(messageRepository.findAll()).thenReturn(Flux.just(Message.of("Hi", "Test", "This is a test")));

		webTestClient
				.get()
				.uri("/api/messages")
				.exchange()
				.expectStatus().isOk()
				.returnResult(Message.class)
				.getResponseBody()
				.as(StepVerifier::create)
				.expectNextCount(1)
				.expectComplete();

		verify(messageRepository).findAll();
	}

	@Test
	void testPostMessage() {
		final var message = Message.of("Hi", "Test", "This is a test");

		when(messageRepository.save(any())).thenReturn(Mono.just(message));

		webTestClient
				.post()
				.uri("/api/messages")
				.body(Mono.just(message), Message.class)
				.exchange()
				.expectStatus().isOk()
				.returnResult(Message.class)
				.getResponseBody()
				.as(StepVerifier::create)
				.expectNextCount(1)
				.expectComplete();

		verify(messageRepository).save(any(Message.class));
	}
}
