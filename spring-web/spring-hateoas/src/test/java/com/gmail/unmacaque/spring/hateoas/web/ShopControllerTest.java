package com.gmail.unmacaque.spring.hateoas.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.endsWith;
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
	void testGetItems() throws Exception {
		mvc.perform(get("/items"))
				.andExpectAll(
						status().isOk(),
						jsonPath("_embedded.items").exists()
				);
	}

	@Test
	void testGetItem() throws Exception {
		mvc.perform(get("/items/1"))
				.andExpectAll(
						status().isOk(),
						jsonPath("itemId").value(1),
						jsonPath("title").exists(),
						jsonPath("description").exists(),
						jsonPath("price").exists(),
						jsonPath("_links.self.href").value(endsWith("/items/1"))
				);
	}

	@Test
	void testOrderItem() throws Exception {
		mvc.perform(post("/items/1/order"))
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
