package com.gmail.unmacaque.spring.ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.from(Application::main).with(TestApplicationConfiguration.class).run(args);
	}

	@TestConfiguration(proxyBeanMethods = false)
	static class TestApplicationConfiguration {

		@SuppressWarnings("resource")
		@Bean
		public GenericContainer<?> ollamaContainer(DynamicPropertyRegistry properties) {
			final var container = new GenericContainer<>(DockerImageName.parse("ollama/ollama"))
					.withExposedPorts(11434);
			properties.add("spring.ai.ollama.base-url", () -> "http://" + container.getHost() + ":" + container.getFirstMappedPort());
			return container;
		}
	}
}
