package com.gmail.unmacaque.spring.domain;

import com.gmail.unmacaque.spring.config.DataConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import reactor.test.StepVerifier;

@DataMongoTest
@Import(DataConfiguration.class)
class QuoteRepositoryTest {

	@Autowired
	private QuoteRepository repository;

	@Test
	void test() {
		StepVerifier
				.create(repository.findWithTailableCursorBy())
				.expectNextCount(5)
				.thenCancel()
				.verify();
	}
}
