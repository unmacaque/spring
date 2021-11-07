package com.gmail.unmacaque.spring.rsocket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class MessageControllerTest {

	private MessageController controller;

	@BeforeEach
	void beforeEach() {
		controller = new MessageController();
	}

	@Test
	void testFireAndForget() {
		StepVerifier
				.create(controller.fireAndForget("Test"))
				.verifyComplete();
	}

	@Test
	void testRequestResponse() {
		StepVerifier
				.create(controller.requestResponse())
				.expectNext("Hello World")
				.verifyComplete();
	}

	@Test
	void testRequestStream() {
		StepVerifier
				.create(controller.requestStream().take(1))
				.expectNextCount(1)
				.verifyComplete();
	}
}
