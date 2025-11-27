package com.gmail.unmacaque.spring.data.rest;

import com.gmail.unmacaque.spring.data.rest.domain.Item;
import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@Transactional
class ApplicationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private JacksonTester<@NonNull Item> json;

	static Item createItem() {
		final var item = new Item();
		item.setTitle("foo");
		item.setDescription("A description for foo");
		item.setPrice(BigDecimal.valueOf(1.99));
		item.setStock(1);
		return item;
	}

	@Test
	void testGetItems() throws Exception {
		mvc.perform(get("/items"))
				.andExpectAll(
						status().isOk(),
						jsonPath("$._embedded.items.length()").value(3)
				);
	}

	@Test
	void testGetItem() throws Exception {
		mvc.perform(get("/items/1"))
				.andExpectAll(
						status().isOk(),
						jsonPath("$.title").value("CPU")
				);
	}

	@Test
	void testPostItem() throws Exception {
		mvc.perform(post("/items")
						.content(json.write(createItem()).getJson()))
				.andExpect(status().isCreated());
	}

	@Test
	void testPutItem() throws Exception {
		mvc.perform(put("/items/2")
						.content(json.write(createItem()).getJson()))
				.andExpect(status().isNoContent());
	}

	@Test
	void testDeleteItem() throws Exception {
		mvc.perform(delete("/items/1"))
				.andExpect(status().isNoContent());
	}
}
