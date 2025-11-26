package com.gmail.unmacaque.spring.resttemplate.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.restclient.test.autoconfigure.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.anything;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(RestTemplateService.class)
class RestTemplateServiceTest {

	@Autowired
	private RestTemplateService service;

	@Autowired
	private MockRestServiceServer server;

	@Test
	void testService() {
		server.expect(once(), anything()).andRespond(
				withSuccess()
						.body("{\"content\":\"Hello World\"}")
						.contentType(MediaType.APPLICATION_JSON)
		);

		final String result = service.doCall();
		server.verify();

		assertThat(result).isEqualTo("Hello World");
	}

}
