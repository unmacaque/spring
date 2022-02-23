package com.gmail.unmacaque.spring.actuate.endpoint;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MyEndpointTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void testEndpoint() throws Exception {
		mvc.perform(get("/actuator/my-endpoint"))
				.andExpectAll(
						status().isOk(),
						jsonPath("message").value("Hello World")
				);
	}
}
