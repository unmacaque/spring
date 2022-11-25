package com.gmail.unmacaque.spring.react.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class WebControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void test() throws Exception {
		mvc.perform(get("/api/"))
				.andExpectAll(
						status().isOk(),
						content().contentType(MediaType.APPLICATION_JSON)
				);
	}
}
