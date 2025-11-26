package com.gmail.unmacaque.spring.data.r2dbc.domain;

import com.gmail.unmacaque.spring.data.r2dbc.config.DataConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.r2dbc.test.autoconfigure.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import reactor.test.StepVerifier;

@DataR2dbcTest
@Import(DataConfiguration.class)
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
