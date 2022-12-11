package com.gmail.unmacaque.spring.micrometer.metrics.web;

import io.micrometer.core.instrument.MeterRegistry;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.actuate.observability.AutoConfigureObservability;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureObservability
class WebControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private MeterRegistry registry;

	@Test
	void testConfiguration() {
		assertThat(registry.find("example.longtasktimer").longTaskTimer()).isNotNull();
		assertThat(registry.find("example.random").gauge()).isNotNull();
	}

	@Test
	void testCounter() throws Exception {
		mvc.perform(get("/counter"))
				.andExpectAll(
						status().isOk()
				);

		assertThat(registry.find("example.counter").counter())
				.isNotNull()
				.satisfies(counter -> assertThat(counter.count()).isCloseTo(1.0, Offset.offset(1e-16)));
	}

	@Test
	void testGauge() throws Exception {
		mvc.perform(get("/gauge"))
				.andExpectAll(
						status().isOk()
				);

		assertThat(registry.find("example.gauge").gauge()).isNotNull();
	}

	@Test
	void testTimer() throws Exception {
		mvc.perform(get("/timer"))
				.andExpectAll(
						status().isOk()
				);

		assertThat(registry.find("example.timer").timer())
				.isNotNull()
				.satisfies(timer -> assertThat(timer.count()).isEqualTo(1));
	}

	@Test
	void testTimed() throws Exception {
		mvc.perform(get("/timed"))
				.andExpectAll(
						status().isOk()
				);

		assertThat(registry.find("example.timed").timer())
				.isNotNull()
				.satisfies(timer -> assertThat(timer.count()).isEqualTo(1));
	}

	@Test
	void testSummary() throws Exception {
		mvc.perform(get("/summary"))
				.andExpectAll(
						status().isOk()
				);

		assertThat(registry.find("example.summary").summary())
				.isNotNull()
				.satisfies(summary -> assertThat(summary.count()).isEqualTo(10));
	}
}
