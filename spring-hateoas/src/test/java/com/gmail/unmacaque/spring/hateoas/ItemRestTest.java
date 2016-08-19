package com.gmail.unmacaque.spring.hateoas;

import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ItemRestTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testGetAll() throws Exception {
		ResponseEntity<Resources<Resource<Item>>> exchange = restTemplate.exchange(
				"/",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Resources<Resource<Item>>>() {});

		assertThat(exchange.getStatusCode(), equalTo(HttpStatus.OK));
	}

	@Test
	public void testGetItem() throws Exception {
		ResponseEntity<Resource<Item>> exchange = restTemplate.exchange(
				"/1",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Resource<Item>>() {});

		assertThat(exchange.getStatusCode(), equalTo(HttpStatus.OK));

		Resource<Item> body = exchange.getBody();
		Item item = body.getContent();

		assertThat(item, hasProperty("itemId", equalTo(1)));
	}
}
