package com.gmail.unmacaque.spring.security.mfa.web;

import com.gmail.unmacaque.spring.security.mfa.config.SecurityConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.authority.FactorGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WebController.class)
@Import(SecurityConfiguration.class)
class WebControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	@WithMockUser(authorities = {FactorGrantedAuthority.OTT_AUTHORITY, FactorGrantedAuthority.PASSWORD_AUTHORITY})
	void testIndex() throws Exception {
		mvc.perform(get("/"))
				.andExpectAll(
						status().isOk(),
						content().string(containsString("successfully authenticated"))
				);
	}

	@Test
	@WithMockUser
	void testHelloWithNoAuthenticationRedirectToLogin() throws Exception {
		mvc.perform(get("/"))
				.andExpectAll(
						status().isFound(),
						redirectedUrlPattern("/login*")
				);
	}

}
