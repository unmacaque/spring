package com.gmail.unmacaque.spring.observability.opentelemetry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.grafana.LgtmStackContainer;
import org.testcontainers.utility.DockerImageName;

public class IntegrationApplication {
	static void main(String[] args) {
		SpringApplication.from(Application::main).with(IntegrationApplicationConfiguration.class).run(args);
	}

	@TestConfiguration(proxyBeanMethods = false)
	static class IntegrationApplicationConfiguration {

		@Bean
		@RestartScope
		@ServiceConnection
		LgtmStackContainer lgtmStackContainer() {
			return new LgtmStackContainer(DockerImageName.parse("grafana/otel-lgtm"));
		}
	}
}
