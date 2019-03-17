package com.gmail.unmacaque.spring.domain;

import com.gmail.unmacaque.spring.config.DataConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@DataMongoTest
@Import(DataConfiguration.class)
public class QuoteRepositoryTest {

	@Autowired
	private QuoteRepository repository;

	@Test
	public void test() {
		StepVerifier
				.create(repository.findWithTailableCursorBy())
				.expectNextCount(5)
				.thenCancel()
				.verify();
	}
}
