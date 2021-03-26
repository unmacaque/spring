package com.gmail.unmacaque.spring;

import com.gmail.unmacaque.spring.domain.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

@SpringBootTest
@AutoConfigureWebTestClient
class ApplicationTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void getMessages() {
		Flux<Message> messages = webTestClient
				.get()
				.uri("/api/messages")
				.accept(MediaType.APPLICATION_NDJSON)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
				.returnResult(Message.class)
				.getResponseBody();

		StepVerifier.create(messages)
				.expectNextCount(2)
				.thenCancel()
				.verify(Duration.ofSeconds(10));
	}

	@Test
	void postMessage() {
		Message message = createMessage("Hello", "unmacaque", "It works!");

		webTestClient
				.post()
				.uri("/api/messages")
				.body(Mono.just(message), Message.class)
				.exchange()
				.expectStatus().isOk();
	}

	private static Message createMessage(String title, String author, String content) {
		Message message = new Message();
		message.setTitle(title);
		message.setAuthor(author);
		message.setContent(content);
		return message;
	}
}
