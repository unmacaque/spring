package com.gmail.unmacaque.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;

@Configuration
@EnableConfigurationProperties(RSocketProperties.class)
public class RSocketConfiguration {

	@Autowired
	private RSocketProperties rSocketProperties;

	@Bean
	public RSocketRequester rSocketRequester(RSocketRequester.Builder builder) {
		return builder
				.tcp(rSocketProperties.getClient().getHost(), rSocketProperties.getClient().getPort());
	}

}
