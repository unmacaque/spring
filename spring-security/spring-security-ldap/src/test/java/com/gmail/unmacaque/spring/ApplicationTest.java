package com.gmail.unmacaque.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void testLogin_withValidCredentials() throws Exception {
		mvc.perform(get("/").with(httpBasic("test", "test")))
				.andExpect(status().isOk())
				.andExpect(content().string("Hello, test"));
	}

	@Test
	public void testLogin_withInvalidCredentials_unauthorized() throws Exception {
		mvc.perform(get("/").with(httpBasic("foo", "bar")))
				.andExpect(status().isUnauthorized());
	}
}
