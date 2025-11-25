package com.gmail.unmacaque.spring.webflux.webclient.config;

import com.gmail.unmacaque.spring.webflux.webclient.domain.WebFluxService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(ClientProperties.class)
public class ClientConfiguration {

	@Bean
	WebFluxService webFluxService(ClientProperties clientProperties) {
		return new WebFluxService(clientProperties.baseUrl());
	}
}
