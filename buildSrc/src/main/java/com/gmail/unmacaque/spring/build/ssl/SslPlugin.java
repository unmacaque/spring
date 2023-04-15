package com.gmail.unmacaque.spring.build.ssl;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.SourceSetContainer;

public class SslPlugin implements Plugin<Project> {

	public static final String CERTS_EXTENSION = "certs";

	public static final String EXTENSION_NAME = "ssl";

	public static final String TASK_NAME = "generateSslCertificates";

	@Override
	public void apply(Project project) {
		final var extension = project.getExtensions().create(EXTENSION_NAME, SslPluginExtension.class);
		final var container = project.container(NamedCertificateDescription.class);

		project.getPlugins().withType(JavaPlugin.class, java -> {
			final var sourceSetContainer = project.getExtensions().getByType(SourceSetContainer.class);
			final var mainSourceSet = sourceSetContainer.getByName(SourceSet.MAIN_SOURCE_SET_NAME).getName();

			final var outputDir = project.getLayout().getBuildDirectory().dir("resources/" + mainSourceSet + "/ssl");
			extension.getOutputDir().convention(outputDir);
		});

		final var sslTask = project.getTasks().create(TASK_NAME, SslTask.class);
		sslTask.setProperty("outputDir", extension.getOutputDir());
	}
}
