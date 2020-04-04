package com.gmail.unmacaque.spring.config;

import io.spring.initializr.generator.project.contributor.ProjectContributor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Files;

@Configuration
public class InitializrConfiguration {

	@Bean
	public ProjectContributor helloProjectContributor() {
		return path -> Files.writeString(path.resolve("hello.txt"), "Hello World");
	}

}
