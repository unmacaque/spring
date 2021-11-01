package com.gmail.unmacaque.spring.config;

import com.gmail.unmacaque.spring.domain.ForecastServiceGrpc;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(GrpcProperties.class)
public class GrpcConfiguration {

	@Autowired
	private GrpcProperties grpcProperties;

	@Bean
	public ForecastServiceGrpc.ForecastServiceBlockingStub forecastService() {
		final var channel = ManagedChannelBuilder
				.forAddress(grpcProperties.getClient().getHost(), grpcProperties.getClient().getPort())
				.usePlaintext()
				.build();
		return ForecastServiceGrpc.newBlockingStub(channel);
	}
}
