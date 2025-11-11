package com.gmail.unmacaque.spring.webflux.webclient;

import com.gmail.unmacaque.spring.webflux.webclient.domain.ClientErrorException;
import com.gmail.unmacaque.spring.webflux.webclient.domain.ServerErrorException;
import com.gmail.unmacaque.spring.webflux.webclient.domain.WebFluxService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.wiremock.spring.EnableWireMock;
import reactor.test.StepVerifier;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest
@EnableWireMock
@ActiveProfiles("test")
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
