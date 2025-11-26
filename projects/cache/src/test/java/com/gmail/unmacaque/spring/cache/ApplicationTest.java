package com.gmail.unmacaque.spring.cache;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void testGetBooks() throws Exception {
		mvc.perform(get("/")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpectAll(
						status().isOk(),
						jsonPath("$.length()").value(12),
						jsonPath("$.[0].id").exists(),
						jsonPath("$.[0].author").exists(),
						jsonPath("$.[0].title").exists(),
						jsonPath("$.[0].genre").exists(),
						jsonPath("$.[0].price").exists(),
						jsonPath("$.[0].publish-date").exists(),
						jsonPath("$.[0].description").exists()
				);
	}

}
