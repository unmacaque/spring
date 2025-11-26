package com.gmail.unmacaque.spring.security.x509;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.client.RestTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
@ActiveProfiles("test")
class ApplicationWithWebEnvironmentTest {

	@Autowired
	private RestTestClient restTestClient;

	@Test
	void testGet() {
		restTestClient
				.get()
				.uri("/")
				.exchange()
				.expectStatus().isOk();
	}

	@Test
	void testActuatorInfo() {
		restTestClient
				.get()
				.uri("/actuator/info")
				.exchange()
				.expectBody().jsonPath("$.ssl.bundles[0].name").isEqualTo("server");
	}

}
