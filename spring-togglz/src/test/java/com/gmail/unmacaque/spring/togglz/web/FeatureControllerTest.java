package com.gmail.unmacaque.spring.togglz.web;

import com.gmail.unmacaque.spring.togglz.togglz.Features;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.togglz.junit5.AllDisabled;
import org.togglz.testing.TestFeatureManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class FeatureControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void testFeatureA() throws Exception {
		mvc.perform(get("/feature/FEATURE_A"))
				.andExpectAll(
						status().isOk(),
						content().string("enabled")
				);
	}

	@Test
	@AllDisabled(Features.class)
	void testFeatureB(TestFeatureManager manager) throws Exception {
		assertThat(manager.isActive(Features.FEATURE_B)).isFalse();

		mvc.perform(get("/feature/FEATURE_B"))
				.andExpectAll(
						status().isOk(),
						content().string("disabled")
				);
	}
}
