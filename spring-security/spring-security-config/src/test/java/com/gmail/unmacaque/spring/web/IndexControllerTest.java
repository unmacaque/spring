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
public class IndexControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testIndex() throws Exception {
		mockMvc.perform(formLogin().user("user").password("user"))
				.andExpect(status().isFound())
				.andExpect(authenticated().withUsername("user").withRoles("USER"))
				.andExpect(redirectedUrl("/"));
	}

	@Test
	@WithMockUser(username = "user", roles = "USER")
	public void testIndex_withRoleUser() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("username", equalTo("user")));
	}

	@Test
	@WithAnonymousUser
	public void testLogin_withoutAuthentication() throws Exception {
		mockMvc.perform(get("/login"))
				.andExpect(status().isOk());
	}
}
