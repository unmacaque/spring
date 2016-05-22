package com.gmail.unmacaque.spring.hateoas;

import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.gmail.unmacaque.spring.Application;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest
public class ItemRestTest {

	@Test
	public void testGetAll() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Resources<Resource<Item>>> exchange = restTemplate.exchange(
				"http://localhost:8080/",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Resources<Resource<Item>>>() {});

		assertThat(exchange.getStatusCode(), equalTo(HttpStatus.OK));
	}

	@Test
	public void testGetItem() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Resource<Item>> exchange = restTemplate.exchange(
				"http://localhost:8080/1",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Resource<Item>>() {});

		assertThat(exchange.getStatusCode(), equalTo(HttpStatus.OK));

		Resource<Item> body = exchange.getBody();
		Item item = body.getContent();

		assertThat(item, hasProperty("itemId", equalTo(1)));
	}
}