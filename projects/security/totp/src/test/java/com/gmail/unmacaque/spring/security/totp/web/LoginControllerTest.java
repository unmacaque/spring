package com.gmail.unmacaque.spring.security.totp.web;

import com.gmail.unmacaque.spring.security.totp.config.SecurityConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginController.class)
@Import(SecurityConfiguration.class)
class LoginControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void testHelloWithoutAuthentication() throws Exception {
		mvc.perform(get("/login"))
				.andExpectAll(status().isOk());
	}

}
