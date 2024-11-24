package com.gmail.unmacaque.spring.security.x509;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.json.BasicJsonTester;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalManagementPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestClient;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureJsonTesters
@ActiveProfiles("test")
class ApplicationWithWebEnvironmentTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private BasicJsonTester json;

	@LocalManagementPort
	private int managementPort;

	@Test
	void testGet() {
		final var entity = restTemplate.getForEntity("/", String.class);

		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void testActuatorInfo() {
		final var info = RestClient.builder()
				.baseUrl("http://localhost:{port}")
				.defaultUriVariables(Map.of("port", managementPort))
				.build()
				.get()
				.uri("/actuator/info")
				.retrieve()
				.body(String.class);

		assertThat(json.from(info)).hasJsonPathStringValue("$.ssl.bundles[0].name", "server");
	}

	@TestConfiguration(proxyBeanMethods = false)
	static class RestTemplateBuilderConfiguration {

		@Bean
		public RestTemplateBuilder restTemplateBuilder(SslBundles sslBundles) {
			final var bundle = sslBundles.getBundle("test");
			return new RestTemplateBuilder().sslBundle(bundle);
		}

	}
}
