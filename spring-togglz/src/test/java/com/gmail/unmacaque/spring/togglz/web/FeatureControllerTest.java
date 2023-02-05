package com.gmail.unmacaque.spring.togglz.web;

import com.gmail.unmacaque.spring.togglz.togglz.TogglzFeature;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.togglz.junit5.AllDisabled;
import org.togglz.junit5.AllEnabled;
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

	@ParameterizedTest
	@EnumSource(TogglzFeature.class)
	void testFeatures(TogglzFeature feature) throws Exception {
		mvc.perform(get("/feature/{name}", feature.name()))
				.andExpectAll(
						status().isOk(),
						content().string(feature.isActive() ? "enabled" : "disabled")
				);
	}

	@ParameterizedTest
	@EnumSource(TogglzFeature.class)
	@AllDisabled(TogglzFeature.class)
	void testFeaturesAllDisabled(TogglzFeature feature, TestFeatureManager manager) throws Exception {
		assertThat(manager.isActive(feature)).isFalse();

		mvc.perform(get("/feature/{name}", feature.name()))
				.andExpectAll(
						status().isOk(),
						content().string("disabled")
				);
	}

	@ParameterizedTest
	@EnumSource(TogglzFeature.class)
	@AllEnabled(TogglzFeature.class)
	void testFeaturesAllEnabled(TogglzFeature feature, TestFeatureManager manager) throws Exception {
		assertThat(manager.isActive(feature)).isTrue();

		mvc.perform(get("/feature/{name}", feature.name()))
				.andExpectAll(
						status().isOk(),
						content().string("enabled")
				);
	}
}
