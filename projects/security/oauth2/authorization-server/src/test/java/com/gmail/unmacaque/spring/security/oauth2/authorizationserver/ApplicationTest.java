package com.gmail.unmacaque.spring.security.oauth2.authorizationserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasItems;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void testAuthenticated() throws Exception {
		mvc.perform(get("/.well-known/openid-configuration"))
				.andExpectAll(
						status().isOk(),
						jsonPath("issuer").exists(),
						jsonPath("authorization_endpoint").exists(),
						jsonPath("token_endpoint").exists(),
						jsonPath("userinfo_endpoint").exists(),
						jsonPath("response_types_supported").value("code"),
						jsonPath("grant_types_supported").value(
								hasItems("authorization_code", "client_credentials", "refresh_token")),
						jsonPath("scopes_supported").value("openid")
				);
	}

}
