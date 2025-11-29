package com.gmail.unmacaque.spring.boot.docker.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WebController.class)
class WebControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void testRoot() throws Exception {
		mvc.perform(get("/"))
				.andExpectAll(
						status().isOk(),
						content().string("Hello World")
				);
	}

}
