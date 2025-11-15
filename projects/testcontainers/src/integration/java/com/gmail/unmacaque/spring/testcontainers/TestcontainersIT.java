package com.gmail.unmacaque.spring.testcontainers;

import org.junit.jupiter.api.*;
import org.mockserver.client.MockServerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MockServerContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@Tag("testcontainers")
class TestcontainersIT {

	public static final DockerImageName MOCKSERVER_IMAGE = DockerImageName
			.parse("mockserver/mockserver")
			.withTag("mockserver-" + MockServerClient.class.getPackage().getImplementationVersion());

	@Container
	private static final MockServerContainer container = new MockServerContainer(MOCKSERVER_IMAGE);

	private static MockServerClient mockServerClient;

	@Autowired
	private MockMvc mvc;

	@BeforeAll
	static void beforeAll() {
		mockServerClient = new MockServerClient(container.getHost(), container.getFirstMappedPort());
	}

	@AfterAll
	static void afterAll() {
		mockServerClient.close();
	}

	@AfterEach
	void afterEach() {
		mockServerClient.reset();
	}

	@Test
	void testGetGreeting() throws Exception {
		mockServerClient
				.when(request().withPath("/"))
				.respond(response().withBody("Hello World"));

		mvc.perform(get("/"))
				.andExpectAll(
						status().isOk(),
						content().string("Hello World")
				);

		mockServerClient.verify(request().withPath("/"));
	}

	@Test
	void testGetGreetingWithBadRequest() throws Exception {
		mockServerClient
				.when(request().withPath("/"))
				.respond(response().withStatusCode(400).withBody("Bad Request"));

		mvc.perform(get("/"))
				.andExpectAll(
						status().isInternalServerError(),
						content().string("Bad Request")
				);

		mockServerClient.verify(request().withPath("/"));
	}

	@TestConfiguration
	static class TestcontainersITTestConfiguration {
		@Bean
		public RestTemplateBuilder restTemplateBuilder() {
			return new RestTemplateBuilder().rootUri(container.getEndpoint());
		}
	}

}
