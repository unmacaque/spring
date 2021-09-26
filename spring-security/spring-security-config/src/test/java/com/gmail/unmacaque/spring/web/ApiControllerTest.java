package com.gmail.unmacaque.spring.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApiControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testApi() throws Exception {
		mockMvc.perform(get("/api")
						.with(httpBasic("admin", "admin")))
				.andExpectAll(
						status().isOk(),
						content().string("admin")
				);
	}

	@Test
	void testApi_withUser_isForbidden() throws Exception {
		mockMvc.perform(get("/api")
						.with(httpBasic("user", "user")))
				.andExpect(status().isForbidden());
	}

	@Test
	@WithMockUser(username = "admin", roles = "ADMIN")
	void testApi_withRoleAdmin() throws Exception {
		mockMvc.perform(get("/api"))
				.andExpectAll(
						status().isOk(),
						content().string("admin")
				);
	}

	@Test
	@WithMockUser(username = "user", roles = "USER")
	void testApi_withRoleUser_isForbidden() throws Exception {
		mockMvc.perform(get("/api"))
				.andExpect(status().isForbidden());
	}
}
