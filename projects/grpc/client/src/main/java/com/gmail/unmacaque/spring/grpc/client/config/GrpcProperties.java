package com.gmail.unmacaque.spring.grpc.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("grpc")
public record GrpcProperties(GrpcProperties.Client client) {

	public record Client(String host, int port) {}
}
