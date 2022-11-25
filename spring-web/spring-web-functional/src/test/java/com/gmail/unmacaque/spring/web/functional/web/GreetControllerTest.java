package com.gmail.unmacaque.spring.web.functional.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class GreetControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void testGreet() throws Exception {
		mvc.perform(get("/"))
				.andExpect(jsonPath("$.message").value("Hello World"));
	}

	@Test
	void testGreetWithName() throws Exception {
		mvc.perform(post("/Spring"))
				.andExpect(jsonPath("$.message").value("Spring"));
	}
}
