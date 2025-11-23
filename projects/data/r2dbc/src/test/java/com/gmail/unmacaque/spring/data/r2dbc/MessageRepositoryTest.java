package com.gmail.unmacaque.spring.data.r2dbc;

import com.gmail.unmacaque.spring.data.r2dbc.config.DataConfiguration;
import com.gmail.unmacaque.spring.data.r2dbc.domain.MessageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
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
