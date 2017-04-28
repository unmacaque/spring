package com.gmail.unmacaque.spring.web;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GreetControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testGreet() throws Exception {
		Greeting exchange = restTemplate.getForObject("/", Greeting.class);

		assertThat(exchange).hasFieldOrPropertyWithValue("message", "Hello World");
	}

	@Test
	public void testGreetWithName() throws Exception {
		Greeting exchange = restTemplate.getForObject("/Spring", Greeting.class);

		assertThat(exchange).hasFieldOrPropertyWithValue("message", "Spring");
	}
}
