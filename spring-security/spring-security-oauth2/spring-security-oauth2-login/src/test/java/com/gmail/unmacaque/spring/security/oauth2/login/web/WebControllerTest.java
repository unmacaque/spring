package com.gmail.unmacaque.spring.security.oauth2.login.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oidcLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WebControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ClientRegistrationRepository registrationRepository;

	@Test
	void testIndex() throws Exception {
		mvc.perform(get("/")
						.with(oidcLogin()))
				.andExpectAll(
						status().isOk(),
						authenticated().withUsername("user"),
						content().string("user")
				);
	}

}
