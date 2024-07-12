package com.gmail.unmacaque.spring.serviceproxy.webclient.config;

import com.gmail.unmacaque.spring.serviceproxy.webclient.domain.ReservationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.web.service.invoker.ReactorHttpExchangeAdapter;

@Configuration(proxyBeanMethods = false)
public class ProxyConfiguration {

	@Bean
	public ReactorHttpExchangeAdapter httpClientAdapter(WebClient.Builder builder) {
		final var client = builder.baseUrl("http://localhost:8888").build();
		return WebClientAdapter.create(client);
	}

	@Bean
	public ReservationService reservationService(ReactorHttpExchangeAdapter adapter) {
		final var factory = HttpServiceProxyFactory.builderFor(adapter).build();
		return factory.createClient(ReservationService.class);
	}
}
