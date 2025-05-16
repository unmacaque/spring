package com.gmail.unmacaque.spring.grpc.client;

import com.gmail.unmacaque.spring.grpc.domain.ForecastServiceGrpc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.grpc.client.ImportGrpcClients;

@SpringBootApplication
@ImportGrpcClients(basePackageClasses = ForecastServiceGrpc.ForecastServiceStub.class)
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
