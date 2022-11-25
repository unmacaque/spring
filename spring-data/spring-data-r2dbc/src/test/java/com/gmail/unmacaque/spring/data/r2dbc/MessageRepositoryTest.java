package com.gmail.unmacaque.spring.data.r2dbc;

import com.gmail.unmacaque.spring.data.r2dbc.domain.MessageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

@SpringBootTest
class MessageRepositoryTest {

	@Autowired
	private MessageRepository repository;

	@Test
	void testFindAll() {
		repository.findAll()
				.as(StepVerifier::create)
				.expectNextCount(1)
				.verifyComplete();
	}

	@Test
	void testFindById() {
		repository.findById(1L)
				.as(StepVerifier::create)
				.expectNextCount(1)
				.verifyComplete();
	}
}
