package com.gmail.unmacaque.spring.security.oauth2.client.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Client;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oidcLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WebController.class)
class WebControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockitoBean
	private ResourceService resourceService;

	@Test
	void testIndex() throws Exception {
		when(resourceService.retrieveResource()).thenReturn("test");

		mvc.perform(get("/")
						.with(oidcLogin())
						.with(oauth2Client("authorization-server")))
				.andExpectAll(
						status().isOk(),
						jsonPath("$", not(empty()))
				);

		verify(resourceService).retrieveResource();
	}

}
