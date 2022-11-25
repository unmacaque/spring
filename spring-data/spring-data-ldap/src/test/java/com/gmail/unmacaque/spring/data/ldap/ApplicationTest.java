package com.gmail.unmacaque.spring.data.ldap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void testGetPersons() throws Exception {
		mvc.perform(get("/persons"))
				.andExpectAll(
						status().isOk(),
						content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
						jsonPath("$").isNotEmpty()
				);
	}

	@Test
	void testGetPersonByName() throws Exception {
		mvc.perform(get("/persons?name=*Doe"))
				.andExpectAll(
						status().isOk(),
						content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
						jsonPath("$.length()").value(2)
				);
	}

	@Test
	void testGetPersonByUid() throws Exception {
		mvc.perform(get("/persons/fred"))
				.andExpectAll(
						status().isOk(),
						content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
						jsonPath("name").value("Fred"),
						jsonPath("uid").value("fred")
				);
	}

	@Test
	void testGetGroups() throws Exception {
		mvc.perform(get("/groups"))
				.andExpectAll(
						status().isOk(),
						content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
						jsonPath("$").isNotEmpty()
				);
	}

	@Test
	void testGetGroupByMember() throws Exception {
		mvc.perform(get("/groups?member=jadoe"))
				.andExpectAll(
						status().isOk(),
						content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
						jsonPath("$.length()").value(2)
				);
	}

}
