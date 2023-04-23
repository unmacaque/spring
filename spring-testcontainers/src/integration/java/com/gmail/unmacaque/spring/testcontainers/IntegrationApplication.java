package com.gmail.unmacaque.spring.testcontainers;

import org.mockserver.client.MockServerClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MockServerContainer;
import org.testcontainers.utility.DockerImageName;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class IntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.from(Application::main).with(IntegrationApplicationConfiguration.class).run(args);
	}

	@TestConfiguration(proxyBeanMethods = false)
	static class IntegrationApplicationConfiguration {

		@Bean
		public MockServerContainer mockServerContainer() {
			return new MockServerContainer(DockerImageName.parse("mockserver/mockserver"));
		}

		@Bean
		public MockServerClient mockServerClient(MockServerContainer container) {
			final var mockServerClient = new MockServerClient(container.getHost(), container.getFirstMappedPort());
			mockServerClient
					.when(request().withPath("/"))
					.respond(response().withBody("Currently in development"));
			return mockServerClient;
		}

		@Bean
		public RestTemplateBuilder restTemplateBuilder(MockServerContainer container) {
			return new RestTemplateBuilder().rootUri(container.getEndpoint());
		}
	}
}
