package com.gmail.unmacaque.spring.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BooksRestControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void testGetBooks() throws Exception {
		mvc.perform(get("/")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", equalTo(12)))
				.andExpect(jsonPath("$.[0].id", notNullValue()))
				.andExpect(jsonPath("$.[0].author", notNullValue()))
				.andExpect(jsonPath("$.[0].title", notNullValue()))
				.andExpect(jsonPath("$.[0].genre", notNullValue()))
				.andExpect(jsonPath("$.[0].price", notNullValue()))
				.andExpect(jsonPath("$.[0].publish-date", notNullValue()))
				.andExpect(jsonPath("$.[0].description", notNullValue()));
	}

}
