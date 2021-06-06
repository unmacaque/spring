package com.gmail.unmacaque.spring.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class GreetingJsonTest {

	@Autowired
	private JacksonTester<Greeting> tester;

	@Test
	void testSerialize() throws IOException {
		final var greeting = new Greeting("Spring Boot", LocalDateTime.of(2017, 4, 17, 0, 0));

		final var json = tester.write(greeting);

		assertThat(json).hasJsonPathStringValue("@.message", "Spring Boot");
	}

	@Test
	void testDeserialize() throws IOException {
		final String json = "{\"date\":\"2017-04-17T00:00:00\",\"message\":\"Spring Boot\"}";

		final var greeting = tester.parseObject(json);

		assertThat(greeting.getDate()).isEqualTo(LocalDateTime.of(2017, 4, 17, 0, 0));
		assertThat(greeting.getMessage()).isEqualTo("Spring Boot");
	}
}
