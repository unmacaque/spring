package com.gmail.unmacaque.spring.testcontainers.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.restclient.test.autoconfigure.AutoConfigureMockRestServiceServer;
import org.springframework.boot.restclient.test.autoconfigure.AutoConfigureRestClient;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServiceUnavailable;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WebController.class)
@AutoConfigureRestClient
@AutoConfigureMockRestServiceServer
class WebControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private MockRestServiceServer server;

	@Test
	void testGetSuccessful() throws Exception {
		server.expect(once(), requestTo("/"))
				.andRespond(
						withSuccess().body("Hello World")
				);

		mvc.perform(get("/"))
				.andExpectAll(
						status().isOk(),
						content().string("Hello World")
				);

		server.verify();
	}

	@Test
	void testGetFailure() throws Exception {
		server.expect(once(), requestTo("/"))
				.andRespond(withServiceUnavailable());

		mvc.perform(get("/"))
				.andExpectAll(
						status().isInternalServerError(),
						content().string("Oh no!")
				);

		server.verify();
	}

}
