package com.gmail.unmacaque.spring.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(GreetController.class)
class GreetControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void testGreet() throws Exception {
		mvc.perform(get("/")).andExpect(content().json("{\"message\":\"Hello World\"}"));
	}

	@Test
	void testGreetWithName() throws Exception {
		mvc.perform(get("/Spring")).andExpect(content().json("{\"message\":\"Spring\"}"));
	}
}
