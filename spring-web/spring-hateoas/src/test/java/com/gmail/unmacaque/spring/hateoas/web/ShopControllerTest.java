package com.gmail.unmacaque.spring.hateoas.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
				.andExpectAll(
						status().isOk(),
						jsonPath("_embedded").exists()
				);
	}

	@Test
	void testGetItem() throws Exception {
		mvc.perform(get("/1"))
				.andExpectAll(
						status().isOk(),
						jsonPath("itemId").value(1),
						jsonPath("title").exists(),
						jsonPath("description").exists(),
						jsonPath("price").exists(),
						jsonPath("_links.self.href").exists()
				);
	}

	@Test
	void testOrderItem() throws Exception {
		mvc.perform(post("/1/order"))
				.andExpectAll(
						status().isCreated(),
						jsonPath("itemId").value(1),
						jsonPath("title").exists(),
						jsonPath("description").exists(),
						jsonPath("price").exists(),
						jsonPath("_links.self.href").exists()
				);
	}

	@Test
	void testOrderItemDoesNotExistNotFound() throws Exception {
		mvc.perform(post("/99/order"))
				.andExpect(status().isNotFound());
	}
}
