package com.gmail.unmacaque.spring.boot.actuator.actuate.health;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HealthGroupsTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void testLiveness() throws Exception {
		mvc.perform(get("/actuator/health/liveness"))
				.andExpectAll(
						status().isOk(),
						jsonPath("status").value("UP")
				);
	}

	@Test
	void testReadiness() throws Exception {
		mvc.perform(get("/actuator/health/readiness"))
				.andExpectAll(
						status().isOk(),
						jsonPath("status").value("UP")
				);
	}
}
