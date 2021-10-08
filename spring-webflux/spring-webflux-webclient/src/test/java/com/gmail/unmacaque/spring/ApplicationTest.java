package com.gmail.unmacaque.spring;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.gmail.unmacaque.spring.domain.ClientErrorException;
import com.gmail.unmacaque.spring.domain.ServerErrorException;
import com.gmail.unmacaque.spring.webflux.WebFluxService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@WireMockTest(httpPort = 8888)
@SpringBootTest
class ApplicationTest {

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
				.expectError(ClientErrorException.class)
				.verify();

		verify(getRequestedFor(urlEqualTo("/")));
	}

	@Test
	void testInternalServerError() {
		stubFor(get(urlEqualTo("/")).willReturn(serverError()));

		StepVerifier.create(service.doCall())
				.expectError(ServerErrorException.class)
				.verify();

		verify(getRequestedFor(urlEqualTo("/")));
	}

}
