package com.gmail.unmacaque.spring.angular.config;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@Configuration
@EnableR2dbcAuditing
public class DataConfiguration {

	@Bean
	public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
		final var initializer = new ConnectionFactoryInitializer();
		initializer.setConnectionFactory(connectionFactory);

		final var populator = new ResourceDatabasePopulator(new ClassPathResource("init.sql"));
		initializer.setDatabasePopulator(populator);
		return initializer;
	}
}
