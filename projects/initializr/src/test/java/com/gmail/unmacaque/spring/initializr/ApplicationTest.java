package com.gmail.unmacaque.spring.initializr;

import io.spring.initializr.web.mapper.InitializrMetadataVersion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void testGetMetadata() throws Exception {
		mvc.perform(get("/")
						.accept(InitializrMetadataVersion.V2_2.getMediaType()))
				.andExpectAll(
						jsonPath("type.default").value("maven-project"),
						jsonPath("type.values.*.id").value(containsInAnyOrder("maven-project", "maven-build", "gradle-project", "gradle-build")),
						jsonPath("packaging.default").value("jar"),
						jsonPath("packaging.values.*.id").value(containsInAnyOrder("jar", "war")),
						jsonPath("javaVersion.default").value("25"),
						jsonPath("language.default").value("java"),
						jsonPath("language.values.*.id").value(containsInAnyOrder("groovy", "java", "kotlin")),
						jsonPath("bootVersion.default").value(SpringBootVersion.getVersion()),
						jsonPath("groupId.default").value("com.gmail.unmacaque.spring")
				);
	}

	@Test
	void testGradleBuild() throws Exception {
		mvc.perform(get("/build.gradle"))
				.andExpectAll(
						status().is2xxSuccessful(),
						content().contentType(MediaType.APPLICATION_OCTET_STREAM)
				);
	}

	@Test
	void testMavenBuild() throws Exception {
		mvc.perform(get("/pom.xml"))
				.andExpectAll(
						status().is2xxSuccessful(),
						content().contentType(MediaType.APPLICATION_OCTET_STREAM)
				);
	}

}
