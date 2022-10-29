package com.gmail.unmacaque.spring.config;

import io.opentelemetry.exporter.logging.LoggingSpanExporter;
import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ObservationConfiguration {

	@Bean
	public LoggingSpanExporter loggingSpanExporter() {
		return LoggingSpanExporter.create();
	}

	@Bean
	@Profile("otel-collector")
	public OtlpHttpSpanExporter otlpHttpMetricExporter() {
		return OtlpHttpSpanExporter.builder().build();
	}
}
