package com.gmail.unmacaque.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@SpringBootApplication
public class Application extends RepositoryRestConfigurerAdapter {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
