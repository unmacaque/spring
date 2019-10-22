package com.gmail.unmacaque.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.x509;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void testGetIsUnauthorized() throws Exception {
		mvc.perform(get("/"))
				.andExpect(status().isForbidden());
	}

	@Test
	void testGetWithX509IsAuthorized() throws Exception {
		mvc.perform(get("/").with(x509("classpath:client.crt")))
				.andExpect(status().isOk())
				.andExpect(authenticated().withUsername("localhost"));
	}
}
