package com.gmail.unmacaque.spring.data;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ItemRestTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testGetItems() throws Exception {
		mockMvc.perform(get("/items"))
		.andExpect(status().isOk());
	}

	@Test
	public void testGetItem() throws Exception {
		mockMvc.perform(get("/items/1"))
		.andExpect(status().isOk());
	}

	@Test
	public void testPostItem() throws Exception {
		mockMvc.perform(post("/items")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"title\":\"foo\",\"description\":\"bar\",\"stock\":1,\"price\":1.99}"))
		.andExpect(status().isCreated());
	}

	@Test
	public void testPutItem() throws Exception {
		mockMvc.perform(put("/items/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"title\":\"foo\",\"description\":\"bar\",\"stock\":1,\"price\":1.99}"))
		.andExpect(status().isNoContent());
	}

	@Test
	public void testDeleteItem() throws Exception {
		mockMvc.perform(delete("/items/1"))
		.andExpect(status().isNoContent());
	}
}
