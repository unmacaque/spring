package com.gmail.unmacaque.spring.build.ssl;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class SslPlugin implements Plugin<Project> {

	public static final String CERTS_EXTENSION = "certs";

	public static final String EXTENSION_NAME = "ssl";

	public static final String TASK_NAME = "generateSslCertificates";

	@Override
	public void apply(Project project) {
		final var extension = project.getExtensions().create(EXTENSION_NAME, SslPluginExtension.class);
		final var container = project.container(NamedCertificateDescription.class);

		project.getTasks().create(TASK_NAME, SslTask.class);
	}
}
