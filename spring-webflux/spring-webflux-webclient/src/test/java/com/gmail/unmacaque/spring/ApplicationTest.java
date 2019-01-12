package com.gmail.unmacaque.spring;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.gmail.unmacaque.spring.webflux.WebFluxService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(8888);

	@Autowired
	private WebFluxService service;

	@Test
	public void test() {
		stubFor(get(urlEqualTo("/"))
				.willReturn(aResponse().withBody("Hello World")));

		service.doCall();

		verify(getRequestedFor(urlEqualTo("/")));
	}

}
