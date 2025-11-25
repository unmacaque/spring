package com.gmail.unmacaque.spring.serviceproxy.webclient.config;

import com.gmail.unmacaque.spring.serviceproxy.webclient.domain.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.web.service.invoker.ReactorHttpExchangeAdapter;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(ProxyProperties.class)
public class ProxyConfiguration {

	@Autowired
	private ProxyProperties proxyProperties;

	@Bean
	ReactorHttpExchangeAdapter httpClientAdapter(WebClient.Builder builder) {
		final var client = builder.baseUrl(proxyProperties.baseUrl()).build();
		return WebClientAdapter.create(client);
	}

	@Bean
	ReservationService reservationService(ReactorHttpExchangeAdapter adapter) {
		final var factory = HttpServiceProxyFactory.builderFor(adapter).build();
		return factory.createClient(ReservationService.class);
	}
}
