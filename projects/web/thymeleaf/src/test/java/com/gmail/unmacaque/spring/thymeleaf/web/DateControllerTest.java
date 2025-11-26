package com.gmail.unmacaque.spring.thymeleaf.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DateController.class)
class DateControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void testDate() throws Exception {
		mvc.perform(get("/date"))
				.andExpectAll(
						status().isOk(),
						model().attributeExists("date")
				);
	}
}
