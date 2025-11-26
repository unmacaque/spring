package com.gmail.unmacaque.spring.restdocs.webtestclient;

import com.gmail.unmacaque.spring.restdocs.webtestclient.domain.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.restdocs.test.autoconfigure.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;

@SpringBootTest
@AutoConfigureRestDocs
@ExtendWith(RestDocumentationExtension.class)
class ApplicationTest {

	@Autowired
	private ApplicationContext applicationContext;

	private WebTestClient webTestClient;

	private static Message createMessage(String title, String author, String content) {
		final var message = new Message();
		message.setTitle(title);
		message.setAuthor(author);
		message.setContent(content);
		return message;
	}

	@BeforeEach
	void beforeEach(RestDocumentationContextProvider restDocumentation) {
		webTestClient = WebTestClient.bindToApplicationContext(applicationContext)
				.configureClient()
				.filter(documentationConfiguration(restDocumentation))
				.build();
	}

	@Test
	void getMessages() {
		webTestClient
				.get()
				.uri("/messages")
				.exchange()
				.expectStatus().isOk()
				.expectBodyList(Message.class).hasSize(2)
				.consumeWith(document("{method-name}"));
	}

	@Test
	void postMessages() {
		final var message = createMessage("Hello", "unmacaque", "It works!");

		webTestClient
				.post()
				.uri("/messages")
				.body(Mono.just(message), Message.class)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.consumeWith(document("{method-name}"));
	}
}
