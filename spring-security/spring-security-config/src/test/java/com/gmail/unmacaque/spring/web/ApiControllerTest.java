package com.gmail.unmacaque.spring.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ApiControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testApi() throws Exception {
		mockMvc.perform(get("/api")
				.with(httpBasic("admin", "admin")))
				.andExpect(status().isOk())
				.andExpect(content().string("admin"));
	}

	@Test
	public void testApi_withUser_isForbidden() throws Exception {
		mockMvc.perform(get("/api")
				.with(httpBasic("user", "user")))
				.andExpect(status().isForbidden());
	}

	@Test
	@WithMockUser(username = "admin", roles = "ADMIN")
	public void testApi_withRoleAdmin() throws Exception {
		mockMvc.perform(get("/api"))
				.andExpect(status().isOk())
				.andExpect(content().string("admin"));
	}

	@Test
	@WithMockUser(username = "user", roles = "USER")
	public void testApi_withRoleUser_isForbidden() throws Exception {
		mockMvc.perform(get("/api"))
				.andExpect(status().isForbidden());
	}
}
