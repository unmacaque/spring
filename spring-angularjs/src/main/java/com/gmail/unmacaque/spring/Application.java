package com.gmail.unmacaque.spring;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import com.gmail.unmacaque.spring.angularjs.HsqlTaskRepository;
import com.gmail.unmacaque.spring.angularjs.InMemoryTaskRepository;
import com.gmail.unmacaque.spring.angularjs.TaskRepository;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

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

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
