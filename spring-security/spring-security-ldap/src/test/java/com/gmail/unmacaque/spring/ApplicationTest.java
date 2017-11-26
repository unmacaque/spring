package com.gmail.unmacaque.spring;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ApplicationTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void testLogin_withValidCredentials() throws Exception {
		mvc.perform(formLogin()
				.user("test")
				.password("test"))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("/"));
	}

	@Test
	public void testLogin_withInvalidCredentials_redirectToLogin() throws Exception {
		mvc.perform(formLogin()
				.user("invalid")
				.password("invalid"))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("/login?error"));
	}
}
