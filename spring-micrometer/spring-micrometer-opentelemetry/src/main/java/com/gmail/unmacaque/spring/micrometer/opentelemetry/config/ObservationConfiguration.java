package com.gmail.unmacaque.spring.micrometer.opentelemetry.config;

import io.opentelemetry.exporter.logging.LoggingMetricExporter;
import io.opentelemetry.exporter.logging.LoggingSpanExporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class ObservationConfiguration {

	@Bean
	public LoggingMetricExporter loggingMetricExporter() {
		return LoggingMetricExporter.create();
	}

	@Bean
	public LoggingSpanExporter loggingSpanExporter() {
		return LoggingSpanExporter.create();
	}

}
