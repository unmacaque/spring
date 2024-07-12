package com.gmail.unmacaque.spring.restclient.config;

import org.springframework.boot.web.client.RestClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class RestClientConfiguration {

	@Bean
	public RestClientCustomizer restClientCustomizer() {
		return builder -> builder.defaultHeader("X-Custom-Header", "Hello World");
	}
}
