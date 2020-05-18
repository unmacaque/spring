package com.gmail.unmacaque.spring.config;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.connectionfactory.init.ConnectionFactoryInitializer;
import org.springframework.data.r2dbc.connectionfactory.init.ResourceDatabasePopulator;

@Configuration
public class DataConfiguration {

	@Bean
	public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
		var initializer = new ConnectionFactoryInitializer();
		initializer.setConnectionFactory(connectionFactory);

		var populator = new ResourceDatabasePopulator(new ClassPathResource("init.sql"));
		initializer.setDatabasePopulator(populator);
		return initializer;
	}
}
