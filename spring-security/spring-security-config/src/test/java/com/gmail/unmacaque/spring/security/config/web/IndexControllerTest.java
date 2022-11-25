package com.gmail.unmacaque.spring.security.config.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class IndexControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testIndex() throws Exception {
		mockMvc.perform(formLogin().user("user").password("pass"))
				.andExpectAll(
						status().isFound(),
						authenticated().withUsername("user").withRoles("USER"),
						redirectedUrl("/")
				);
	}

	@Test
	@WithMockUser(username = "user", roles = "USER")
	void testIndex_withRoleUser() throws Exception {
		mockMvc.perform(get("/"))
				.andExpectAll(
						status().isOk(),
						model().attribute("username", "user")
				);
	}

	@Test
	@WithAnonymousUser
	void testLogin_withoutAuthentication() throws Exception {
		mockMvc.perform(get("/login"))
				.andExpect(status().isOk());
	}
}
