package com.gmail.unmacaque.spring;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.gmail.unmacaque.spring.webflux.WebFluxService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.test.StepVerifier;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest
class ApplicationTest {

	@BeforeAll
	static void beforeAll() {
		WireMock.configureFor(8888);
		var server = new WireMockServer(8888);
		server.start();
	}

	@Autowired
	private WebFluxService service;

	@Test
	void testOk() {
		stubFor(get(urlEqualTo("/")).willReturn(ok("Hello World")));

		StepVerifier.create(service.doCall())
				.expectNext("Hello World")
				.verifyComplete();

		verify(getRequestedFor(urlEqualTo("/")));
	}

	@Test
	void testNotFound() {
		stubFor(get(urlEqualTo("/")).willReturn(notFound()));

		StepVerifier.create(service.doCall())
				.expectError(WebClientResponseException.class)
				.verify();

		verify(getRequestedFor(urlEqualTo("/")));
	}

}
