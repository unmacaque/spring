package com.gmail.unmacaque.spring.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
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

}
