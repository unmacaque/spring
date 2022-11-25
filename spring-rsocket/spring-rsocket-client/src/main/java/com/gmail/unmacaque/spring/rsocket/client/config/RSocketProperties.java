package com.gmail.unmacaque.spring.rsocket.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties("rsocket")
@Validated
public record RSocketProperties(@DefaultValue RSocketProperties.Client client) {

	public record Client(@DefaultValue("localhost") String host, @DefaultValue("7000") int port) {}
}
