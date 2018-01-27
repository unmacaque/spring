package com.gmail.unmacaque.spring.domain;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ItemRepositoryTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private JacksonTester<Item> json;

	@Test
	public void testGetItems() throws Exception {
		mvc.perform(get("/items"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", equalTo(3)));
	}

	@Test
	public void testGetItem() throws Exception {
		mvc.perform(get("/items/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.title", equalTo("CPU")));
	}

	@Test
	public void testPostItem() throws Exception {
		mvc.perform(post("/items")
				.content(json.write(createItem()).getJson()))
				.andExpect(status().isCreated());
	}

	@Test
	public void testPutItem() throws Exception {
		mvc.perform(put("/items/2")
				.content(json.write(createItem()).getJson()))
				.andExpect(status().isNoContent());
	}

	@Test
	public void testDeleteItem() throws Exception {
		mvc.perform(delete("/items/1"))
				.andExpect(status().isNoContent());
	}

	public static Item createItem() {
		return Item.create("foo", "A description for foo", BigDecimal.valueOf(1.99), 1);
	}
}
