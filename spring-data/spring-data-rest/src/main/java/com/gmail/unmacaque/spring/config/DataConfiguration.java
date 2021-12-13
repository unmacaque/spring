package com.gmail.unmacaque.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

@Configuration
public class DataConfiguration {

	@Bean
	public Jackson2RepositoryPopulatorFactoryBean repositoryPopulator() {
		final var factory = new Jackson2RepositoryPopulatorFactoryBean();
		factory.setResources(new Resource[]{new ClassPathResource("data.json")});
		return factory;
	}
}
