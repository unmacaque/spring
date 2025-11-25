package com.gmail.unmacaque.spring.security.config.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;

@Configuration(proxyBeanMethods = false)
public class BeanConfiguration {

	@Bean
	SpringSecurityDialect springSecurityDialect() {
		return new SpringSecurityDialect();
	}
}
