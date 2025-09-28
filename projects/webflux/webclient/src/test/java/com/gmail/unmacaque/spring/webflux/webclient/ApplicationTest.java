package com.gmail.unmacaque.spring.webflux.webclient;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.gmail.unmacaque.spring.webflux.webclient.domain.ClientErrorException;
import com.gmail.unmacaque.spring.webflux.webclient.domain.ServerErrorException;
import com.gmail.unmacaque.spring.webflux.webclient.domain.WebFluxService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import reactor.test.StepVerifier;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@SpringBootTest
class ApplicationTest {

	@RegisterExtension
	static WireMockExtension wireMock = WireMockExtension.newInstance()
			.options(wireMockConfig().dynamicPort().dynamicHttpsPort())
			.build();

	@DynamicPropertySource
	static void dynamicPropertiesSource(DynamicPropertyRegistry registry) {
		registry.add("webclient.base-url", wireMock.getRuntimeInfo()::getHttpBaseUrl);
	}

	@Autowired
	private WebFluxService service;

	@Test
	void testOk() {
		wireMock.stubFor(get(urlEqualTo("/")).willReturn(ok("Hello World")));

		StepVerifier.create(service.doCall())
				.expectNext("Hello World")
				.verifyComplete();

		wireMock.verify(getRequestedFor(urlEqualTo("/")));
	}

	@Test
	void testNotFound() {
		wireMock.stubFor(get(urlEqualTo("/")).willReturn(notFound()));

		StepVerifier.create(service.doCall())
				.expectError(ClientErrorException.class)
				.verify();

		wireMock.verify(getRequestedFor(urlEqualTo("/")));
	}

	@Test
	void testInternalServerError() {
		wireMock.stubFor(get(urlEqualTo("/")).willReturn(serverError()));

		StepVerifier.create(service.doCall())
				.expectError(ServerErrorException.class)
				.verify();

		wireMock.verify(getRequestedFor(urlEqualTo("/")));
	}

}
