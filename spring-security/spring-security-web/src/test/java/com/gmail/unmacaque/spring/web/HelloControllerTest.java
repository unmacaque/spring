package com.gmail.unmacaque.spring.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithAnonymousUser
	public void testIndex() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().isOk());
	}

	@Test
	@WithAnonymousUser
	public void testHello_withoutAuthentication_redirectToLogin() throws Exception {
		mockMvc.perform(get("/hello"))
				.andExpect(status().isFound())
				.andExpect(redirectedUrlPattern("**/login"));
	}

	@Test
	@WithMockUser(username = "user", roles = "USER")
	public void testHello_withRoleUser() throws Exception {
		mockMvc.perform(get("/hello"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("username", equalTo("user")));
	}

	@Test
	@WithAnonymousUser
	public void testLogin_withoutAuthentication() throws Exception {
		mockMvc.perform(get("/login"))
				.andExpect(status().isOk());
	}

	@Test
	public void testLogin_withRoleUser() throws Exception {
		mockMvc.perform(formLogin().user("user").password("user"))
				.andExpect(status().isFound())
				.andExpect(authenticated().withUsername("user").withRoles("USER"))
				.andExpect(redirectedUrl("/hello"));
	}

	@Test
	@WithMockUser(username = "user", roles = "USER")
	public void testLogin_withRoleUser_redirectToHello() throws Exception {
		mockMvc.perform(get("/login"))
				.andExpect(status().isOk())
				.andExpect(forwardedUrl("/hello"));
	}

	@Test
	@WithMockUser(username = "admin", roles = "ADMIN")
	public void testAdmin_withRoleAdmin() throws Exception {
		mockMvc.perform(get("/admin"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("username", equalTo("admin")));
	}

	@Test
	@WithMockUser(username = "user", roles = "USER")
	public void testAdmin_withRoleUser_isForbidden() throws Exception {
		mockMvc.perform(get("/admin"))
				.andExpect(status().isForbidden());
	}
}
