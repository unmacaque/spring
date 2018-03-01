package com.gmail.unmacaque.spring.config;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;

@Configuration
public class JpaConfiguration {
	@Bean
	public DateTimeProvider dateTimeProvider() {
		return () -> Optional.of(LocalDateTime.now());
	}
}
