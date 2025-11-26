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
class MyHealthHealthIndicatorTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void testHealthIndicator() throws Exception {
		mvc.perform(get("/actuator/health"))
				.andExpectAll(
						status().isOk(),
						jsonPath("status").value("UP"),
						jsonPath("components.myHealth.status").value("UP")
				);
	}
}
