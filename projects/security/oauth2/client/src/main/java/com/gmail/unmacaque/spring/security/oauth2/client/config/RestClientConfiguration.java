package com.gmail.unmacaque.spring.security.oauth2.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.client.OAuth2ClientHttpRequestInterceptor;
import org.springframework.web.client.RestClient;

@Configuration(proxyBeanMethods = false)
public class RestClientConfiguration {

	@Bean
	RestClient.Builder restClientBuilder(OAuth2AuthorizedClientManager authorizedClientManager) {
		final var requestInterceptor = new OAuth2ClientHttpRequestInterceptor(authorizedClientManager);

		return RestClient.builder()
				.baseUrl("http://localhost:8180")
				.requestInterceptor(requestInterceptor);
	}
}
