package com.gmail.unmacaque.spring.security.x509;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.restclient.RestClientCustomizer;
import org.springframework.boot.restclient.autoconfigure.RestClientSsl;
import org.springframework.boot.restclient.test.autoconfigure.AutoConfigureRestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.server.LocalManagementPort;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestClient
@ActiveProfiles("test")
class ApplicationWithWebEnvironmentTest {

	@Autowired
	private RestClient.Builder restClientBuilder;

	@LocalServerPort
	private int port;

	@LocalManagementPort
	private int managementPort;

	@Test
	void testGet() {
		final var response = restClientBuilder
				.baseUrl("https://localhost:" + port)
				.build()
				.get()
				.uri("/")
				.retrieve()
				.toBodilessEntity();

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void testActuatorInfo() {
		final var response = restClientBuilder
				.baseUrl("http://localhost:" + managementPort)
				.build()
				.get()
				.uri("/actuator/info")
				.retrieve()
				.toBodilessEntity();

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@TestConfiguration
	static class RestTestClientSslConfiguration {

		@Bean
		RestClientCustomizer restTestClientBuilderCustomizer(RestClientSsl ssl) {
			return builder -> builder.apply(ssl.fromBundle("test"));
		}
	}
}
