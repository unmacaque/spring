package com.gmail.unmacaque.spring.config;

import io.spring.initializr.generator.project.contributor.ProjectContributor;
import io.spring.initializr.metadata.DefaultMetadataElement;
import io.spring.initializr.web.support.InitializrMetadataUpdateStrategy;
import org.springframework.boot.SpringBootVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Files;
import java.util.List;

@Configuration
public class InitializrConfiguration {

	@Bean
	public ProjectContributor helloProjectContributor() {
		return path -> Files.writeString(path.resolve("hello.txt"), "Hello World");
	}

	@Bean
	public InitializrMetadataUpdateStrategy initializrMetadataUpdateStrategy() {
		return metadata -> {
			final DefaultMetadataElement version = DefaultMetadataElement.create(SpringBootVersion.getVersion(), true);
			metadata.updateSpringBootVersions(List.of(version));
			return metadata;
		};
	}

}
