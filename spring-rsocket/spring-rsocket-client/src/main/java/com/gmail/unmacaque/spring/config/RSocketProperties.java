package com.gmail.unmacaque.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties("rsocket")
@ConstructorBinding
@Validated
public record RSocketProperties(@DefaultValue RSocketProperties.Client client) {

	public static record Client(@DefaultValue("localhost") String host, @DefaultValue("7000") int port) {}
}
