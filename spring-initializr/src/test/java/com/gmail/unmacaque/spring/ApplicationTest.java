package com.gmail.unmacaque.spring;

import io.spring.initializr.web.mapper.InitializrMetadataVersion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void testMetadata() throws Exception {
		mvc.perform(get("/")
				.accept(InitializrMetadataVersion.V2_2.getMediaType()))
				.andExpect(jsonPath("$.type.default", equalTo("maven-project")))
				.andExpect(jsonPath("$.type.values.*.id", containsInAnyOrder("maven-project", "maven-build", "gradle-project", "gradle-build")))
				.andExpect(jsonPath("$.packaging.default", equalTo("jar")))
				.andExpect(jsonPath("$.packaging.values.*.id", containsInAnyOrder("jar", "war")))
				.andExpect(jsonPath("$.javaVersion.default", equalTo("11")))
				.andExpect(jsonPath("$.language.default", equalTo("java")))
				.andExpect(jsonPath("$.language.values.*.id", containsInAnyOrder("groovy", "java", "kotlin")))
				.andExpect(jsonPath("$.bootVersion.default", equalTo(SpringBootVersion.getVersion())))
				.andExpect(jsonPath("$.groupId.default", equalTo("com.gmail.unmacaque.spring")));
	}

}
