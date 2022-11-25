package com.gmail.unmacaque.spring.angular.domain;

import com.gmail.unmacaque.spring.angular.config.DataConfiguration;
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
				.expectNextCount(2)
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
