package com.gmail.unmacaque.spring.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(GreetController.class)
public class GreetControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void testGreet() throws Exception {
		mvc.perform(get("/")).andExpect(content().json("{\"message\":\"Hello World\"}"));
	}

	@Test
	public void testGreetWithName() throws Exception {
		mvc.perform(get("/Spring")).andExpect(content().json("{\"message\":\"Spring\"}"));
	}
}
