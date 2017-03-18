package com.gmail.unmacaque.spring.domain;

import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.net.URI;

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
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.gmail.unmacaque.spring.domain.Item;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ItemRepositoryTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testGetItems() throws Exception {
		ResponseEntity<Resources<Resource<Item>>> exchange = restTemplate.exchange(
				"/items",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Resources<Resource<Item>>>() {});

		assertThat(exchange.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(exchange.getBody().getContent(), hasSize(2));
	}

	@Test
	public void testGetItem() throws Exception {
		ResponseEntity<Resource<Item>> exchange = restTemplate.exchange(
				"/items/1",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Resource<Item>>() {});

		assertThat(exchange.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(exchange.getBody().getContent(), hasProperty("title", equalTo("CPU")));
	}

	@Test
	public void testPostItem() throws Exception {
		Item item = new Item("foo", "bar", BigDecimal.valueOf(1.99), 1);

		ResponseEntity<Void> exchange = restTemplate.postForEntity(
				"/items",
				item,
				Void.class);

		assertThat(exchange.getStatusCode(), equalTo(HttpStatus.CREATED));
	}

	@Test
	public void testPutItem() throws Exception {
		Item item = new Item("foo", "bar", BigDecimal.valueOf(1.99), 1);

		ResponseEntity<Void> exchange = restTemplate.exchange(
				"/items/2",
				HttpMethod.PUT,
				RequestEntity.put(URI.create("/")).body(item),
				Void.class);

		assertThat(exchange.getStatusCode(), equalTo(HttpStatus.OK));
	}

	@Test
	public void testDeleteItem() throws Exception {
		ResponseEntity<Void> exchange = restTemplate.exchange(
				"/items/1",
				HttpMethod.DELETE,
				null,
				Void.class);

		assertThat(exchange.getStatusCode(), equalTo(HttpStatus.NO_CONTENT));
	}
}
