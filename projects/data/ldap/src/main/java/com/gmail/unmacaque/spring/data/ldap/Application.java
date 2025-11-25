package com.gmail.unmacaque.spring.data.ldap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;

@SpringBootApplication
@EnableLdapRepositories
public class Application {
	static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
