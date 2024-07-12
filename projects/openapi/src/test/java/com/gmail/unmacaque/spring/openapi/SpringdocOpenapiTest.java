package com.gmail.unmacaque.spring.openapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SpringdocOpenapiTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void testSwaggerUi() throws Exception {
		mvc.perform(get("/swagger-ui.html"))
				.andExpect(status().isFound());
	}

	@Test
	void testOpenapiJson() throws Exception {
		mvc.perform(get("/v3/api-docs"))
				.andExpectAll(
						status().isOk(),
						content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
						jsonPath("openapi", startsWith("3."))
				);
	}

	@Test
	void testOpenapiYaml() throws Exception {
		mvc.perform(get("/v3/api-docs.yaml"))
				.andExpectAll(
						status().isOk(),
						content().contentTypeCompatibleWith("application/vnd.oai.openapi")
				);
	}
}
