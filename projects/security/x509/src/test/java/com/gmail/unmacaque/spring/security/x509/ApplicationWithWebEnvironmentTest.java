package com.gmail.unmacaque.spring.security.x509;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ApplicationWithWebEnvironmentTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void testGet() {
		final var entity = restTemplate.getForEntity("/", String.class);

		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
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
