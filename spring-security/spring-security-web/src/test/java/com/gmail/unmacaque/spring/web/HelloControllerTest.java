package com.gmail.unmacaque.spring.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class HelloControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testIndex() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().isOk());
	}

	@Test
	public void testHello_withoutAuthentication_redirectToLogin() throws Exception {
		mockMvc.perform(get("/hello"))
				.andExpect(status().isFound())
				.andExpect(redirectedUrlPattern("**/login"));
	}

	@Test
	public void testHello_withRoleUser() throws Exception {
		mockMvc.perform(get("/hello")
				.with(user("user").roles("USER")))
				.andExpect(status().isOk())
				.andExpect(model().attribute("username", equalTo("user")));
	}

	@Test
	public void testHello_withoutAuthentication() throws Exception {
		mockMvc.perform(get("/login"))
				.andExpect(status().isOk());
	}

	@Test
	public void testLogin_withRoleUser_redirectToHello() throws Exception {
		mockMvc.perform(get("/login")
				.with(user("user").roles("USER")))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("/hello"));
	}

	@Test
	public void testAdmin_withRoleAdmin() throws Exception {
		mockMvc.perform(get("/admin")
				.with(user("admin").roles("ADMIN")))
				.andExpect(status().isOk())
				.andExpect(model().attribute("username", equalTo("admin")));
	}

	@Test
	public void testAdmin_withRoleUser_isForbidden() throws Exception {
		mockMvc.perform(get("/admin")
				.with(user("somebody")))
				.andExpect(status().isForbidden());
	}
}
