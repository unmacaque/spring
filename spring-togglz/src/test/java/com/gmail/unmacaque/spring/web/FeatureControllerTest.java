package com.gmail.unmacaque.spring.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FeatureControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void testIndex() throws Exception {
		mvc.perform(get("/"))
				.andExpect(status().isOk());
	}

	@Test
	public void testFeatureA() throws Exception {
		mvc.perform(get("/feature/FEATURE_A"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("feature", "FEATURE_A"));
	}

	@Test
	public void testFeatureB() throws Exception {
		mvc.perform(get("/feature/FEATURE_B"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("feature", "FEATURE_B"));
	}

	@Test
	public void testFeatureC() throws Exception {
		mvc.perform(get("/feature/FEATURE_C"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("feature", "FEATURE_C"));
	}
}
