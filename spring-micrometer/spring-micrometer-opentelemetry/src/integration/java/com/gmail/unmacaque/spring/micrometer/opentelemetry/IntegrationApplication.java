package com.gmail.unmacaque.spring.micrometer.opentelemetry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.MountableFile;

public class IntegrationApplication {
	public static void main(String[] args) {
		SpringApplication.from(Application::main).with(IntegrationApplicationConfiguration.class).run(args);
	}

	@TestConfiguration(proxyBeanMethods = false)
	static class IntegrationApplicationConfiguration {

		@Bean
		@ServiceConnection(name = "otel/opentelemetry-collector-contrib")
		public GenericContainer<?> otelCollectorContainer() {
			return new GenericContainer<>("otel/opentelemetry-collector-contrib")
					.withExposedPorts(4318)
					.withCopyFileToContainer(
							MountableFile.forClasspathResource("otel-collector-config.yaml"),
							"/etc/otelcol-contrib/config.yaml"
					)
					.withLabel("org.springframework.boot.readiness-check.tcp.disable", "true");
		}
	}
}
