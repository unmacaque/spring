package com.gmail.unmacaque.spring;

import javax.servlet.Filter;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hsqldb.jdbc.JDBCDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.springsecurity3.dialect.SpringSecurityDialect;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	@Bean
	public DataSource dataSource() {
		JDBCDataSource dataSource = new JDBCDataSource();
		dataSource.setUrl("jdbc:hsqldb:mem:spring");
		dataSource.setUser("");
		dataSource.setPassword("");
		return dataSource;
	}

	@Bean
	public Filter hiddenHttpMethodFilter() {
		return new HiddenHttpMethodFilter();
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		return messageSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setConfigLocation(new ClassPathResource("hibernate.cfg.xml"));
		sessionFactory.setPackagesToScan("com.gmail.unmacaque.spring.security.model");
		return sessionFactory;
	}

	@Bean
	public TemplateEngine templateEngine() {
		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.addDialect(new SpringSecurityDialect());
		return templateEngine;
	}

	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		return transactionManager;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
