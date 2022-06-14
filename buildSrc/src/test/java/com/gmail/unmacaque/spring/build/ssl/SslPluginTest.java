package com.gmail.unmacaque.spring.build.ssl;

import org.gradle.testfixtures.ProjectBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SslPluginTest {

	@Test
	void projectHasSslTask() {
		final var project = ProjectBuilder.builder().build();
		project.getPluginManager().apply("unmacaque.ssl");

		assertTrue(project.getTasks().getByName("generateSslCertificates") instanceof SslTask);
	}
}
