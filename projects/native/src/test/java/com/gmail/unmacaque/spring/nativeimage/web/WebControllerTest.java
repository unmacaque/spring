package com.gmail.unmacaque.spring.nativeimage.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WebController.class)
class WebControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void testGetCountryCodes() throws Exception {
		mvc.perform(get("/"))
				.andExpectAll(
						status().isOk(),
						content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
						jsonPath("$").exists(),
						jsonPath("$").value(everyItem(matchesPattern("[A-Z]{2}")))
				);
	}

	@Test
	void testPositiveMatch() throws Exception {
		mvc.perform(get("/LI"))
				.andExpectAll(
						status().isOk(),
						content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
						jsonPath("Code").value("LI"),
						jsonPath("Name").value("Liechtenstein")
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
