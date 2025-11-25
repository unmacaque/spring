package com.gmail.unmacaque.spring.ai.rag;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.github.dockerjava.api.model.Bind;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.ollama.OllamaContainer;
import org.testcontainers.utility.DockerImageName;

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

		@SuppressWarnings("rawtypes")
		@Bean
		@RestartScope
		@ServiceConnection
		PostgreSQLContainer chromaDBContainer() {
			return new PostgreSQLContainer(DockerImageName.parse("pgvector/pgvector:pg17"));
		}

		@Bean
		ApplicationRunner applicationRunner(VectorStore vectorStore) {
			return args -> {
				record PromptSuggestions(String act, String prompt, @JsonProperty("for_devs") boolean forDevs) {}

				final var mapper = new CsvMapper();
				final var schema = CsvSchema.emptySchema()
						.withHeader()
						.withColumnSeparator(',');

				final var suggestions = mapper
						.readerWithTypedSchemaFor(PromptSuggestions.class)
						.with(schema)
						.<PromptSuggestions>readValues(new ClassPathResource("prompts.csv").getInputStream())
						.readAll();

				final var documents = suggestions
						.stream()
						.map(s -> new Document(s.act + ": " + s.prompt(), Map.of("forDevs", s.forDevs())))
						.toList();

				vectorStore.add(documents);
			};
		}
	}
}
