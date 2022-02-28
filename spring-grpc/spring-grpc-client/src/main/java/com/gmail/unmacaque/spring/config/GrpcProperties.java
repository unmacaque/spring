package com.gmail.unmacaque.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties("grpc")
@ConstructorBinding
public record GrpcProperties(GrpcProperties.Client client) {

	public record Client(String host, int port) {}
}
