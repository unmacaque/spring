package com.gmail.unmacaque.spring.restclient.config;

import org.springframework.boot.restclient.RestClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class RestClientConfiguration {

	@Bean
	RestClientCustomizer restClientCustomizer() {
		return builder -> builder.defaultHeader("X-Custom-Header", "Hello World");
	}
}
