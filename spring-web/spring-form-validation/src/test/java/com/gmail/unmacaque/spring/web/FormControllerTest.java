package com.gmail.unmacaque.spring.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.gmail.unmacaque.spring.web.FormController;

@RunWith(SpringRunner.class)
@WebMvcTest(FormController.class)
public class FormControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void testGet() throws Exception {
		mvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(model().hasNoErrors());
	}

	@Test
	public void testPost() throws Exception {
		mvc.perform(post("/")
				.param("shortName", "foo")
				.param("shortInteger", "42"))
				.andExpect(status().isOk())
				.andExpect(model().hasNoErrors())
				.andExpect(model().attributeExists("formData"));
	}

	@Test
	public void testPostWithErrors() throws Exception {
		mvc.perform(post("/"))
				.andExpect(status().isOk())
				.andExpect(model().attributeHasFieldErrors("data", "shortName", "shortInteger"))
				.andExpect(model().attributeDoesNotExist("formData"));
	}

	@Test
	public void testPostWithMoreErrors() throws Exception {
		mvc.perform(post("/")
				.param("comment", "<script>alert(1)</script>"))
				.andExpect(status().isOk())
				.andExpect(model().attributeHasFieldErrors("data", "comment"))
				.andExpect(model().attributeDoesNotExist("formData"));
	}
}
