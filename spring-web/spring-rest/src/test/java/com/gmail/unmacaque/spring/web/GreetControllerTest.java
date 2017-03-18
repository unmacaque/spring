package com.gmail.unmacaque.spring.web;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.gmail.unmacaque.spring.web.GreetResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GreetControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testGreet() throws Exception {
		GreetResponse exchange = restTemplate.getForObject("/", GreetResponse.class);

		assertThat(exchange).hasFieldOrPropertyWithValue("response", "anonymous");
	}

	@Test
	public void testGreetWithName() throws Exception {
		GreetResponse exchange = restTemplate.getForObject("/Spring", GreetResponse.class);

		assertThat(exchange).hasFieldOrPropertyWithValue("response", "Spring");
	}
}
