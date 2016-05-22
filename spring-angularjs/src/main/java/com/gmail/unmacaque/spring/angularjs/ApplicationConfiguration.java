package com.gmail.unmacaque.spring.angularjs;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Bean
	public TaskRepository inMemoryTaskRepository() {
		return new InMemoryTaskRepository();
	}

	@Bean
	@Primary
	public TaskRepository taskRepository() {
		return new HsqlTaskRepository(sessionFactory().getObject());
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan("com.gmail.unmacaque.spring.angularjs");
		return sessionFactory;
	}
}
