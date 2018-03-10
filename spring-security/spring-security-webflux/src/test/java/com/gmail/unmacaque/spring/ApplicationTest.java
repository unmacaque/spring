package com.gmail.unmacaque.spring;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

	@Autowired
	private ApplicationContext applicationContext;

	private WebTestClient webTestClient;

	@Before
	public void setup() {
		webTestClient = WebTestClient
				.bindToApplicationContext(applicationContext)
				.apply(springSecurity())
				.configureClient()
				.build();
	}

	@Test
	public void testRoot() {
		webTestClient
				.get()
				.uri("/")
				.exchange()
				.expectStatus()
				.isOk();
	}

	@Test
	@WithMockUser
	public void testHelloName() {
		webTestClient
				.get()
				.uri("/hello/John")
				.exchange()
				.expectStatus()
				.isOk();
	}

	@Test
	public void testHelloNameWithoutAuthenticationIsUnauthorized() {
		webTestClient
				.get()
				.uri("/hello/John")
				.exchange()
				.expectStatus()
				.isUnauthorized();
	}

}
