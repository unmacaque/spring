package com.gmail.unmacaque.spring.apiversioning.web;

import com.gmail.unmacaque.spring.apiversioning.domain.ProfileV1;
import com.gmail.unmacaque.spring.apiversioning.domain.ProfileV2;
import com.gmail.unmacaque.spring.apiversioning.domain.ProfileV3;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.resttestclient.autoconfigure.RestTestClientBuilderCustomizer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.springframework.web.client.ApiVersionInserter;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(ApiController.class)
@AutoConfigureRestTestClient
class ApiControllerTest {

	@Autowired
	private RestTestClient restTestClient;

	@Test
	void testGetProfileV1() {
		restTestClient
				.get()
				.uri("/api/profile")
				.apiVersion(1)
				.exchangeSuccessfully()
				.expectBody(ProfileV1.class).value(
						profileV1 -> {
							assertThat(profileV1).isNotNull();
							assertThat(profileV1.firstName()).isEqualTo("John");
							assertThat(profileV1.lastName()).isEqualTo("Doe");
							assertThat(profileV1.country()).isEqualTo("Fiji");
						}
				);
	}

	@Test
	void testGetProfileV2() {
		restTestClient
				.get()
				.uri("/api/profile")
				.apiVersion(2)
				.exchangeSuccessfully()
				.expectBody(ProfileV2.class).value(
						profileV2 -> {
							assertThat(profileV2).isNotNull();
							assertThat(profileV2.firstName()).isEqualTo("John");
							assertThat(profileV2.lastName()).isEqualTo("Doe");
							assertThat(profileV2.country()).isEqualTo("Fiji");
							assertThat(profileV2.dateOfBirth()).isEqualTo(LocalDate.of(1984, 7, 15));
						}
				);
	}

	@Test
	void testGetProfileV3() {
		restTestClient
				.get()
				.uri("/api/profile")
				.apiVersion(3)
				.exchangeSuccessfully()
				.expectBody(ProfileV3.class).value(
						profileV3 -> {
							assertThat(profileV3).isNotNull();
							assertThat(profileV3.firstName()).isEqualTo("John");
							assertThat(profileV3.lastName()).isEqualTo("Doe");
							assertThat(profileV3.country()).isEqualTo("Fiji");
							assertThat(profileV3.dateOfBirth()).isEqualTo(LocalDate.of(1984, 7, 15));
							assertThat(profileV3.interests()).containsExactlyInAnyOrder("Climbing", "Programming");
						}
				);
	}

	@Test
	void testGetProfileV4() {
		restTestClient
				.get()
				.uri("/api/profile")
				.apiVersion(4)
				.exchange()
				.expectStatus().isBadRequest();
	}

	@TestConfiguration
	static class ApiControllerTestConfiguration {

		@Bean
		RestTestClientBuilderCustomizer apiVersionRestTestClientBuilderCustomizer() {
			return builder -> builder.apiVersionInserter(
					ApiVersionInserter.usePathSegment(1)
			);
		}
	}
}
