package com.gmail.unmacaque.spring.data.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.JacksonRepositoryPopulatorFactoryBean;

@Configuration(proxyBeanMethods = false)
public class DataConfiguration {

	@Bean
	JacksonRepositoryPopulatorFactoryBean repositoryPopulator() {
		final var factory = new JacksonRepositoryPopulatorFactoryBean();
		factory.setResources(new Resource[]{new ClassPathResource("data.json")});
		return factory;
	}
}
