package com.gmail.unmacaque.spring.cors.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WebController.class)
class WebControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void testCorsPreflightGet() throws Exception {
		mvc.perform(options("/")
				.header("Access-Control-Request-Method", "GET")
				.header("Origin", "http://foo.bar")
		).andExpectAll(
				status().isOk(),
				header().string("Access-Control-Allow-Origin", "*"),
				header().string("Access-Control-Allow-Methods", "GET")
		);
	}

	@Test
	void testCorsGet() throws Exception {
		mvc.perform(get("/")
				.header("Access-Control-Request-Method", "GET")
				.header("Origin", "http://foo.bar")
		).andExpectAll(
				status().isOk(),
				content().string("Hello cross-origin request")
		);
	}

	@Test
	void testCorsPreflightPost() throws Exception {
		mvc.perform(options("/")
				.header("Access-Control-Request-Method", "POST")
				.header("Origin", "http://foo.bar")
		).andExpectAll(
				status().isOk(),
				header().string("Access-Control-Allow-Origin", "http://foo.bar"),
				header().string("Access-Control-Allow-Methods", "POST")
		);
	}

	@Test
	void testCorsPost() throws Exception {
		mvc.perform(post("/")
				.content("payload")
				.header("Access-Control-Request-Method", "POST")
				.header("Origin", "http://foo.bar")
		).andExpectAll(
				status().isOk(),
				content().string("accepted payload")
		);
	}
}
