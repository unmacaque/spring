package com.gmail.unmacaque.spring.web;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@JsonTest
public class GreetingJsonTest {

	@Autowired
	private JacksonTester<Greeting> tester;

	@Test
	public void testSerialize() throws IOException {
		Greeting greeting = new Greeting("Spring Boot", LocalDateTime.of(2017, 4, 17, 0, 0));

		JsonContent<Greeting> json = tester.write(greeting);

		assertThat(json).hasJsonPathStringValue("@.message", "Spring Boot");
	}

	@Test
	public void testDeserialize() throws IOException {
		String json = "{\"date\":\"2017-04-17T00:00:00\",\"message\":\"Spring Boot\"}";

		Greeting greeting = tester.parseObject(json);

		assertThat(greeting.getDate()).isEqualTo(LocalDateTime.of(2017, 4, 17, 0, 0));
		assertThat(greeting.getMessage()).isEqualTo("Spring Boot");
	}
}
