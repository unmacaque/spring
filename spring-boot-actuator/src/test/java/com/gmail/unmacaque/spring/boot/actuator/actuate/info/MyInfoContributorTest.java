package com.gmail.unmacaque.spring.boot.actuator.actuate.info;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MyInfoContributorTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void testInfoContributor() throws Exception {
		mvc.perform(get("/actuator/info"))
				.andExpectAll(
						status().isOk(),
						jsonPath("myInfo", notNullValue())
				);
	}
}
