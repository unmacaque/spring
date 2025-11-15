package com.gmail.unmacaque.spring.build.ssl;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Transformer;
import org.gradle.api.file.Directory;
import org.gradle.api.file.RegularFile;
import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.SourceSetContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class SslPlugin implements Plugin<Project> {

	public static final String EXTENSION_NAME = "ssl";

	public static final String TASK_NAME = "generateSslCertificates";

	@NotNull
	private static Transformer<@Nullable RegularFile, Directory> transformToOutputFile(RegularFileProperty property) {
		return directory -> directory.dir("ca").file(property.get().getAsFile().getName());
	}

	@Override
	public void apply(Project project) {
		final var extension = project.getExtensions().create(EXTENSION_NAME, SslPluginExtension.class);

		project.getPlugins().withType(JavaPlugin.class, java -> {
			final var sourceSetContainer = project.getExtensions().getByType(SourceSetContainer.class);
			final var mainSourceSet = sourceSetContainer.getByName(SourceSet.MAIN_SOURCE_SET_NAME).getName();

			final var outputDir = project.getLayout().getBuildDirectory().dir("resources/" + mainSourceSet + "/ssl");
			extension.getOutputDir().convention(outputDir);
		});

		final var sslCaTask = project.getTasks().register(TASK_NAME + "Ca", SslCaTask.class, task -> {
			task.getOutputDir().set(extension.getOutputDir().dir("ca"));
			task.getCert().set(extension.getCa().getCert());
			task.getKey().set(extension.getCa().getKey());
			task.getSubject().set(extension.getCa().getSubject());
			task.getDays().set(extension.getCa().getDays());
		});

		final ArrayList<SslTask> sslTasks = new ArrayList<>();
		extension.getCerts().all(certificate -> {
			final var name = certificate.getName();
			final var taskName = TASK_NAME + name.substring(0, 1).toUpperCase() + name.substring(1);
			final var sslTask = project.getTasks().register(taskName, SslTask.class, task -> {
				task.dependsOn(sslCaTask);
				task.getOutputDir().set(extension.getOutputDir().dir(name));
				task.getCert().set(certificate.getCert());
				task.getKey().set(certificate.getKey());
				task.getSigningCert().set(extension.getOutputDir().map(transformToOutputFile(extension.getCa().getCert())));
				task.getSigningKey().set(extension.getOutputDir().map(transformToOutputFile(extension.getCa().getKey())));
				task.getSubject().set(certificate.getSubject());
				task.getDays().set(certificate.getDays());
			}).get();
			sslTasks.add(sslTask);
		});

		project.getTasks().register(TASK_NAME, task -> {
			task.dependsOn(sslCaTask);
			sslTasks.forEach(sslTask -> {
				task.dependsOn(sslTask);
				sslTask.dependsOn(sslCaTask);
			});
		});

		final var processResources = project.getTasks().findByName("processResources");
		if (processResources != null) {
			processResources.dependsOn(TASK_NAME);
		}
	}
}
