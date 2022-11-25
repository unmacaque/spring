package com.gmail.unmacaque.spring.thymeleaf.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ThymeleafController.class)
class ThymeleafControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void test() throws Exception {
		mvc.perform(get("/"))
				.andExpectAll(
						status().isOk(),
						model().attributeExists("mappings")
				);
	}
}
