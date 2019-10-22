package com.gmail.unmacaque.spring.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FormController.class)
class FormControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void testGet() throws Exception {
		mvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(model().hasNoErrors());
	}

	@Test
	void testPost() throws Exception {
		mvc.perform(post("/")
				.param("shortName", "foo")
				.param("shortInteger", "42"))
				.andExpect(status().isOk())
				.andExpect(model().hasNoErrors())
				.andExpect(model().attributeExists("formData"));
	}

	@Test
	void testPostWithErrors() throws Exception {
		mvc.perform(post("/"))
				.andExpect(status().isOk())
				.andExpect(model().attributeHasFieldErrors("data", "shortName", "shortInteger"))
				.andExpect(model().attributeDoesNotExist("formData"));
	}

	@Test
	void testPostWithMoreErrors() throws Exception {
		mvc.perform(post("/")
				.param("comment", "<script>alert(1)</script>"))
				.andExpect(status().isOk())
				.andExpect(model().attributeHasFieldErrors("data", "comment"))
				.andExpect(model().attributeDoesNotExist("formData"));
	}
}
