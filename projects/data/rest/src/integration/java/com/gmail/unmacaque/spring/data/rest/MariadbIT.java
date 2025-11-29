package com.gmail.unmacaque.spring.data.rest;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mariadb.MariaDBContainer;

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
	@ServiceConnection
	static final MariaDBContainer mariadbContrainer = new MariaDBContainer("mariadb:10");

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
