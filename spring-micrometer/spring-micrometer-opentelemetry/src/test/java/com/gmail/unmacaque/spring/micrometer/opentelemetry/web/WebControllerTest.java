package com.gmail.unmacaque.spring.micrometer.opentelemetry.web;

import io.micrometer.observation.tck.TestObservationRegistry;
import io.micrometer.observation.tck.TestObservationRegistryAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.actuate.observability.AutoConfigureObservability;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureObservability(metrics = false)
class WebControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private TestObservationRegistry registry;

	@Test
	void testHello() throws Exception {
		mvc.perform(get("/user/12345"))
				.andExpectAll(
						status().isOk(),
						content().string("Hello user 12345")
				);

		TestObservationRegistryAssert.assertThat(registry)
				.doesNotHaveAnyRemainingCurrentObservation()
				.hasObservationWithNameEqualTo("spring.observation.hello")
				.that()
				.hasHighCardinalityKeyValue("user.id", "12345")
				.hasLowCardinalityKeyValue("http.method", "GET")
				.doesNotHaveError();
	}

	@Test
	void testError() throws Exception {
		mvc.perform(get("/error"))
				.andExpectAll(
						status().isInternalServerError()
				);

		TestObservationRegistryAssert.assertThat(registry)
				.doesNotHaveAnyRemainingCurrentObservation()
				.hasObservationWithNameEqualTo("spring.observation.error")
				.that()
				.hasError()
				.assertThatError()
				.isInstanceOf(IllegalStateException.class)
				.hasMessage("Oh, no!");
	}

	@TestConfiguration
	static class WebControllerTestConfiguration {
		@Bean
		public TestObservationRegistry testObservationRegistry() {
			return TestObservationRegistry.create();
		}
	}
}
