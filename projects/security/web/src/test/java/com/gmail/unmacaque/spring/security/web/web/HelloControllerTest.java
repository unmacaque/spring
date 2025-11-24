package com.gmail.unmacaque.spring.security.web.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class HelloControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithAnonymousUser
	void testIndex() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().isOk());
	}

	@Test
	@WithAnonymousUser
	void testHelloWithoutAuthenticationRedirectToLogin() throws Exception {
		mockMvc.perform(get("/hello"))
				.andExpectAll(
						status().isFound(),
						redirectedUrlPattern("**/login")
				);
	}

	@Test
	@WithMockUser(username = "user", roles = "USER")
	void testHelloWithRoleUser() throws Exception {
		mockMvc.perform(get("/hello"))
				.andExpectAll(
						status().isOk(),
						model().attribute("username", "user")
				);
	}

	@Test
	@WithAnonymousUser
	void testLoginWithoutAuthentication() throws Exception {
		mockMvc.perform(get("/login"))
				.andExpect(status().isOk());
	}

	@Test
	void testLoginWithRoleUser() throws Exception {
		mockMvc.perform(formLogin().user("user").password("user"))
				.andExpectAll(
						status().isFound(),
						authenticated().withUsername("user").withRoles("USER"),
						redirectedUrl("/hello")
				);
	}

	@Test
	@WithMockUser(username = "user", roles = "USER")
	void testLoginWithLoggedInUserRedirectToHello() throws Exception {
		mockMvc.perform(get("/login"))
				.andExpectAll(
						status().isOk(),
						forwardedUrl("/hello")
				);
	}

	@Test
	@WithMockUser(username = "admin", roles = "ADMIN")
	void testAdminWithRoleAdmin() throws Exception {
		mockMvc.perform(get("/admin"))
				.andExpectAll(
						status().isOk(),
						model().attribute("username", "admin")
				);
	}

	@Test
	@WithMockUser(username = "user", roles = "USER")
	void testAdminWithRoleUserIsForbidden() throws Exception {
		mockMvc.perform(get("/admin"))
				.andExpect(status().isForbidden());
	}
}
