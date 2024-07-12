package com.gmail.unmacaque.spring.grpc.client.config;

import com.gmail.unmacaque.spring.grpc.domain.ForecastServiceGrpc;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(GrpcProperties.class)
public class GrpcConfiguration {

	@Autowired
	private GrpcProperties grpcProperties;

	@Bean
	public ForecastServiceGrpc.ForecastServiceBlockingStub forecastService() {
		final var channel = ManagedChannelBuilder
				.forAddress(grpcProperties.client().host(), grpcProperties.client().port())
				.usePlaintext()
				.build();
		return ForecastServiceGrpc.newBlockingStub(channel);
	}
}
