package com.gmail.unmacaque.spring;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.togglz.console.TogglzConsoleServlet;
import org.togglz.servlet.TogglzFilter;

import com.github.heneke.thymeleaf.togglz.TogglzDialect;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	public TogglzConsoleServlet togglzConsoleServlet() {
		return new TogglzConsoleServlet();
	}

	@Bean
	public FilterRegistrationBean togglzFilterRegistrationBean() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new TogglzFilter());
		registration.setUrlPatterns(Collections.singletonList("/*"));
		return registration;
	}

	@Bean
	public ServletRegistrationBean togglzServletRegistrationBean() {
		ServletRegistrationBean registration = new ServletRegistrationBean(togglzConsoleServlet(), "/togglz/*");
		registration.addInitParameter("TogglzConsoleServlet", "org.togglz.console.TogglzConsoleServlet");
		return registration;
	}

	@Bean
	public TogglzDialect togglzDialect() {
		return new TogglzDialect();
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
