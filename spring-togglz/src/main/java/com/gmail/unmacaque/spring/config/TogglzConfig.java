package com.gmail.unmacaque.spring.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.repository.jdbc.JDBCStateRepository;

@Configuration
public class TogglzConfig {
	@Bean
	@Profile("jdbc")
	public StateRepository jdbcStateRepository(DataSource dataSource) {
		return new JDBCStateRepository(dataSource);
	}
}
