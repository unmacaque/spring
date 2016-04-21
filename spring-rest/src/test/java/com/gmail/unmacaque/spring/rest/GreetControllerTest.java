package com.gmail.unmacaque.spring.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest
public class GreetControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(webApplicationContext)
				.addFilters(new CorsFilterBean())
				.build();
	}

	@Test
	public void testGreet() throws Exception {
		mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(header().string("Access-Control-Allow-Origin", "*"))
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testGreet_withPathVariable() throws Exception {
		mockMvc.perform(get("/hello"))
		.andExpect(status().isOk())
		.andExpect(header().string("Access-Control-Allow-Origin", "*"))
		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(content().json("{'response':'hello'}"));
	}
}
