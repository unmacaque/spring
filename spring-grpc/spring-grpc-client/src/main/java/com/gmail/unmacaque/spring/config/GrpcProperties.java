package com.gmail.unmacaque.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties("grpc")
@ConstructorBinding
public class GrpcProperties {

	private final Client client;

	public GrpcProperties(Client client) {
		this.client = client;
	}

	public Client getClient() {
		return client;
	}

	public static class Client {
		private final String host;

		private final int port;

		public Client(String host, int port) {
			this.host = host;
			this.port = port;
		}

		public String getHost() {
			return host;
		}

		public int getPort() {
			return port;
		}
	}
}
