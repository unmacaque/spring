package com.gmail.unmacaque.spring.restclient.web;

import com.gmail.unmacaque.spring.restclient.config.RestClientConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClientResponseException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(RestClientService.class)
@Import(RestClientConfiguration.class)
class RestClientServiceTest {

	@Autowired
	private RestClientService service;

	@Autowired
	private MockRestServiceServer server;

	@Test
	void testSuccess() {
		server.expect(once(), method(HttpMethod.GET))
				.andExpect(header("X-Custom-Header", "Hello World"))
				.andRespond(
						withSuccess()
								.body("{\"content\":\"Hello World\"}")
								.contentType(MediaType.APPLICATION_JSON)
				);

		final String result = service.doCall();
		server.verify();

		assertThat(result).isEqualTo("Hello World");
	}

	@Test
	void testClientError() {
		server.expect(once(), anything()).andRespond(withStatus(HttpStatus.BAD_REQUEST));

		assertThatCode(() -> service.doCall())
				.isInstanceOfSatisfying(RestClientResponseException.class, e ->
						assertThat(e)
								.extracting(RestClientResponseException::getStatusCode)
								.isEqualTo(HttpStatus.BAD_REQUEST)
				);

		server.verify();
	}

	@Test
	void testServerError() {
		server.expect(once(), anything()).andRespond(withStatus(HttpStatus.INTERNAL_SERVER_ERROR));

		assertThatCode(() -> service.doCall())
				.isInstanceOfSatisfying(RestClientResponseException.class, e ->
						assertThat(e)
								.extracting(RestClientResponseException::getStatusCode)
								.isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR)
				);

		server.verify();
	}
}
