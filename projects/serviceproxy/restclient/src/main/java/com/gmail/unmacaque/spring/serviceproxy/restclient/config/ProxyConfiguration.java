package com.gmail.unmacaque.spring.serviceproxy.restclient.config;

import com.gmail.unmacaque.spring.serviceproxy.restclient.domain.ReservationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration(proxyBeanMethods = false)
public class ProxyConfiguration {

	@Bean
	RestClientAdapter httpClientAdapter(RestClient.Builder builder) {
		final var client = builder.build();
		return RestClientAdapter.create(client);
	}

	@Bean
	ReservationService reservationService(RestClientAdapter adapter) {
		final var factory = HttpServiceProxyFactory.builderFor(adapter).build();
		return factory.createClient(ReservationService.class);
	}
}
