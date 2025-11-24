package com.gmail.unmacaque.spring.security.oauth2.resourceserver.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WebController.class)
class WebControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void testAuthenticated() throws Exception {
		mvc.perform(get("/")
						.with(jwt()
								.jwt(jwt -> jwt
										.claim("scope", OidcScopes.OPENID)
										.claim(JwtClaimNames.SUB, "test")
										.build()
								)
						)
				)
				.andExpectAll(
						status().isOk(),
						authenticated().withUsername("test"),
						content().string("test")
				);
	}

	@Test
	void testUnauthenticated() throws Exception {
		mvc.perform(get("/"))
				.andExpect(status().isUnauthorized());
	}

}
