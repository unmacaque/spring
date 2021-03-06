package com.gmail.unmacaque.spring.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ItemRepositoryTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectFactory<ObjectMapper> objectMapperFactory;

	private JacksonTester<Item> json;

	@BeforeEach
	void before() {
		JacksonTester.initFields(this, objectMapperFactory);
	}

	@Test
	void testGetItems() throws Exception {
		mvc.perform(get("/items"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$._embedded.items.length()", equalTo(3)));
	}

	@Test
	void testGetItem() throws Exception {
		mvc.perform(get("/items/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.title", equalTo("CPU")));
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

	static Item createItem() {
		return Item.create("foo", "A description for foo", BigDecimal.valueOf(1.99), 1);
	}
}
