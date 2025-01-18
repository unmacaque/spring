package com.gmail.unmacaque.spring.security.ott.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WelcomeController.class)
class WelcomeControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	@WithMockUser
	void testWelcome() throws Exception {
		mvc.perform(get("/"))
				.andExpectAll(
						status().isOk(),
						model().attribute("username", "user"),
						view().name("welcome")
				);
	}

	@Test
	@WithAnonymousUser
	void testWelcomeWithoutAuthentication() throws Exception {
		mvc.perform(get("/"))
				.andExpectAll(
						status().isUnauthorized()
				);
	}
}
