package com.gmail.unmacaque.spring.observability.opentelemetry.config;

import io.micrometer.core.instrument.logging.LoggingMeterRegistry;
import io.micrometer.core.instrument.logging.LoggingRegistryConfig;
import io.opentelemetry.exporter.logging.LoggingSpanExporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class ObservationConfiguration {

	@Bean
	LoggingMeterRegistry loggingMeterRegistry() {
		return LoggingMeterRegistry.builder(LoggingRegistryConfig.DEFAULT).build();
	}

	@Bean
	LoggingSpanExporter loggingSpanExporter() {
		return LoggingSpanExporter.create();
	}

}
