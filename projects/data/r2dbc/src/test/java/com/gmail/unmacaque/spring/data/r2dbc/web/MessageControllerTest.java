package com.gmail.unmacaque.spring.data.r2dbc.web;

import com.gmail.unmacaque.spring.data.r2dbc.domain.Message;
import com.gmail.unmacaque.spring.data.r2dbc.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;

@WebFluxTest(MessageController.class)
class MessageControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	@MockitoBean
	private MessageRepository messageRepository;

	@Test
	void testFindAll() {
		webTestClient
				.get()
				.uri("/api")
				.exchangeSuccessfully()
				.expectBody()
				.jsonPath("$.length()").value(Integer.class, o -> assertThat(o).isZero());
	}

	@Test
	void testFindById() {
		doReturn(Mono.just(new Message())).when(messageRepository).findById(eq(123L));

		webTestClient
				.get()
				.uri("/api/123")
				.exchangeSuccessfully()
				.expectBody()
				.jsonPath(".title").exists()
				.jsonPath(".author").exists()
				.jsonPath(".content").exists();
	}
}
