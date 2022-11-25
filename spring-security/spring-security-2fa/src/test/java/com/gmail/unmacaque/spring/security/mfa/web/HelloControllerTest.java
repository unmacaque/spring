package com.gmail.unmacaque.spring.security.mfa.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testIndex() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().isOk());
	}

	@Test
	void testHelloWithNoAuthenticationRedirectToLogin() throws Exception {
		mockMvc.perform(get("/hello"))
				.andExpectAll(
						status().isFound(),
						redirectedUrlPattern("**/login")
				);
	}

	@Test
	void testHelloWithRoleUser() throws Exception {
		mockMvc.perform(get("/hello")
						.with(user("user").roles("USER")))
				.andExpectAll(
						status().isOk(),
						model().attribute("username", "user")
				);
	}

	@Test
	void testHelloWithoutAuthentication() throws Exception {
		mockMvc.perform(get("/login"))
				.andExpect(status().isOk());
	}
}
