package com.gmail.unmacaque.spring.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ShopControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void testGetAll() throws Exception {
		mvc.perform(get("/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("_embedded", notNullValue()));
	}

	@Test
	void testGetItem() throws Exception {
		mvc.perform(get("/1"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("itemId", equalTo(1)))
				.andExpect(jsonPath("title", notNullValue()))
				.andExpect(jsonPath("description", notNullValue()))
				.andExpect(jsonPath("price", notNullValue()))
				.andExpect(jsonPath("_links.self.href", notNullValue()));
	}

	@Test
	void testOrderItem() throws Exception {
		mvc.perform(post("/1/order"))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(jsonPath("itemId", equalTo(1)))
				.andExpect(jsonPath("title", notNullValue()))
				.andExpect(jsonPath("description", notNullValue()))
				.andExpect(jsonPath("price", notNullValue()))
				.andExpect(jsonPath("_links.self.href", notNullValue()));
	}

	@Test
	void testOrderItemDoesNotExistNotFound() throws Exception {
		mvc.perform(post("/99/order"))
				.andDo(print())
				.andExpect(status().isNotFound());
	}
}
