package com.gmail.unmacaque.spring.ai.vectorstore;

import com.github.dockerjava.api.model.Bind;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.ollama.OllamaContainer;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.List;
import java.util.Map;
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

		@Bean
		@RestartScope
		@ServiceConnection
		PostgreSQLContainer pgvectorContainer() {
			return new PostgreSQLContainer(DockerImageName.parse("pgvector/pgvector:pg17"));
		}

		@Bean
		ApplicationRunner applicationRunner(VectorStore vectorStore) {
			return _ -> {
				final List<Document> documents = List.of(
						new Document("Spring AI rocks!! Spring AI rocks!! Spring AI rocks!! Spring AI rocks!! Spring AI rocks!!", Map.of("meta1", "meta1")),
						new Document("The World is Big and Salvation Lurks Around the Corner"),
						new Document("You walk forward facing the past and you turn back toward the future.", Map.of("meta2", "meta2"))
				);

				vectorStore.add(documents);
			};
		}
	}
}
