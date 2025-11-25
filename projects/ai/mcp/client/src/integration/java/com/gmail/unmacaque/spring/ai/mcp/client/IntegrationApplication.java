package com.gmail.unmacaque.spring.ai.mcp.client;

import com.github.dockerjava.api.model.Bind;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.ollama.OllamaContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Objects;

public class IntegrationApplication {

	static void main(String[] args) {
		SpringApplication.from(Application::main).with(TestApplicationConfiguration.class).run(args);
	}

	@TestConfiguration(proxyBeanMethods = false)
	static class TestApplicationConfiguration {

		@SuppressWarnings("resource")
		@Bean
		@RestartScope
		@ServiceConnection
		OllamaContainer ollamaContainer(DynamicPropertyRegistry properties) {
			return new OllamaContainer(DockerImageName.parse("ollama/ollama"))
					.withCreateContainerCmdModifier(cmd -> Objects.requireNonNull(cmd.getHostConfig())
							.withBinds(Bind.parse("ollama:/root/.ollama")));
		}
	}
}
