package com.gmail.unmacaque.spring.web.protobuf;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void getHello() throws Exception {
		mvc.perform(get("/"))
				.andExpectAll(
						status().isOk(),
						content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
						jsonPath("message").value("Hello World")
				);
	}

	@Test
	void postHello() throws Exception {
		mvc.perform(post("/")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"message\":\"Hello Spring Boot!\"}"))
				.andExpectAll(
						status().isOk(),
						content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN),
						content().string("Hello Spring Boot!")
				);
	}

}
