package com.gmail.unmacaque.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties("rsocket")
@ConstructorBinding
@Validated
public class RSocketProperties {

	private final Client client;

	public RSocketProperties(@DefaultValue Client client) {
		this.client = client;
	}

	public Client getClient() {
		return client;
	}

	public static class Client {

		private final String host;

		private final int port;

		public Client(@DefaultValue("localhost") String host, @DefaultValue("7000") int port) {
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
