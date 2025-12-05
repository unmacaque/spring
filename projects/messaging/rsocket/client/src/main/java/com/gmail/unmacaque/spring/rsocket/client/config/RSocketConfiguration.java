package com.gmail.unmacaque.spring.rsocket.client.config;

import com.gmail.unmacaque.spring.rsocket.client.messaging.MessageService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.service.RSocketServiceProxyFactory;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(RSocketProperties.class)
public class RSocketConfiguration {

	@Bean
	MessageService messageService(RSocketRequester.Builder builder, RSocketProperties rSocketProperties) {
		return RSocketServiceProxyFactory
				.builder(builder
						.tcp(rSocketProperties.client().host(), rSocketProperties.client().port())
				)
				.build()
				.createClient(MessageService.class);
	}

}
