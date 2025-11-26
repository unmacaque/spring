package com.gmail.unmacaque.spring.problem.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProblemController.class)
class ProblemControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void testGetError() throws Exception {
		mvc.perform(get("/error"))
				.andExpectAll(
						status().isInternalServerError(),
						content().contentType(MediaType.APPLICATION_PROBLEM_JSON),
						jsonPath("status").value(500),
						jsonPath("title").value("Internal Server Error"),
						jsonPath("detail").value("Oh no, something has gone wrong!")
				);
	}

	@Test
	void testForm() throws Exception {
		mvc.perform(post("/form"))
				.andExpectAll(
						status().isBadRequest(),
						content().contentType(MediaType.APPLICATION_PROBLEM_JSON),
						jsonPath("status").value(400),
						jsonPath("title").value("Bad Request"),
						jsonPath("detail").value("Failed to read request")
				);
	}

}
