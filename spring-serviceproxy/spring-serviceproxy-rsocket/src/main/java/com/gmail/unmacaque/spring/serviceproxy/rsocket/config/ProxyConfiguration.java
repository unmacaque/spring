package com.gmail.unmacaque.spring.serviceproxy.rsocket.config;

import com.gmail.unmacaque.spring.serviceproxy.rsocket.domain.ForecastService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.service.RSocketServiceProxyFactory;

@Configuration(proxyBeanMethods = false)
public class ProxyConfiguration {

	@Bean
	public RSocketRequester rSocketRequester(RSocketRequester.Builder builder) {
		return builder.tcp("localhost", 7000);
	}

	@Bean
	public ForecastService forecastService(RSocketRequester rSocketRequester) {
		final var factory = RSocketServiceProxyFactory.builder(rSocketRequester).build();
		return factory.createClient(ForecastService.class);
	}
}
