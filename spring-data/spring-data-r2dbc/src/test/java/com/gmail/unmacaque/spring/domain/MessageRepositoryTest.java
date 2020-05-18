package com.gmail.unmacaque.spring.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

@SpringBootTest
class MessageRepositoryTest {

	@Autowired
	private MessageRepository repository;

	@Test
	void testFindAll() throws Exception {
		repository.findAll()
				.as(StepVerifier::create)
				.expectNextCount(1)
				.verifyComplete();
	}

	@Test
	void testFindById() throws Exception {
		repository.findById(1L)
				.as(StepVerifier::create)
				.expectNextCount(1)
				.verifyComplete();
	}
}
