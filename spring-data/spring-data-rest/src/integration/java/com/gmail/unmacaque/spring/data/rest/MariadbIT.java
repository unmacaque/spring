package com.gmail.unmacaque.spring.data.rest;


import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("testcontainers")
@AutoConfigureMockMvc
@Testcontainers
@Tag("testcontainers")
class MariadbIT {

	@Container
	private static final MariaDBContainer<?> mariadbContrainer = new MariaDBContainer<>("mariadb:10");

	@Autowired
	private MockMvc mvc;

	@Test
	void testGetItems() throws Exception {
		mvc.perform(get("/items"))
				.andExpectAll(
						status().isOk(),
						jsonPath("_embedded.items.length()").value(3),
						jsonPath("_links.self").exists()
				);
	}
}
