package com.gmail.unmacaque.spring.config;

import com.gmail.unmacaque.spring.domain.ReservationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ProxyConfiguration {

	@Bean
	public HttpClientAdapter httpClientAdapter(WebClient.Builder builder) {
		final var client = builder.baseUrl("http://localhost:8888").build();
		return WebClientAdapter.forClient(client);
	}

	@Bean
	public ReservationService reservationService(HttpClientAdapter adapter) {
		final var factory = HttpServiceProxyFactory.builder(adapter).build();
		return factory.createClient(ReservationService.class);
	}
}
