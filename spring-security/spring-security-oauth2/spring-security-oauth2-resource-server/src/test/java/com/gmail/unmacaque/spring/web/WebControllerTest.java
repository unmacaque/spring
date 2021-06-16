package com.gmail.unmacaque.spring.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WebControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void testAuthenticated() throws Exception {
		mvc.perform(get("/")
				.with(jwt().jwt(jwt -> jwt.claim("scope", "read").claim("sub", "test").build())))
				.andExpect(status().isOk())
				.andExpect(authenticated().withUsername("test"))
				.andExpect(content().string("test"));
	}

	@Test
	void testUnauthenticated() throws Exception {
		mvc.perform(get("/"))
				.andExpect(status().isUnauthorized());
	}

}
