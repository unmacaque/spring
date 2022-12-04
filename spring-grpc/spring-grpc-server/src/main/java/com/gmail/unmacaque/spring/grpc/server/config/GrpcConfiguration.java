package com.gmail.unmacaque.spring.grpc.server.config;

import com.gmail.unmacaque.spring.grpc.server.domain.ForecastService;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class GrpcConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(GrpcConfiguration.class);

	@Bean
	public ApplicationRunner gprcServerRunner() {
		return args -> {
			final var server = ServerBuilder.forPort(9090).addService(new ForecastService()).build();
			logger.info("Starting gRPC server on port {}", 9090);
			server.start();
			server.awaitTermination();
		};
	}
}
