package com.gmail.unmacaque.spring.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WebController.class)
class WebControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void testPositiveMatch() throws Exception {
		mvc.perform(get("/LI"))
				.andExpectAll(
						status().isOk(),
						content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN),
						content().string("Liechtenstein")
				);
	}

	@Test
	void testNegativeMatch() throws Exception {
		mvc.perform(get("/XX"))
				.andExpectAll(
						status().isNotFound()
				);
	}

}
