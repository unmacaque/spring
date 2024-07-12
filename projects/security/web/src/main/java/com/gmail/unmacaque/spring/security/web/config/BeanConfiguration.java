package com.gmail.unmacaque.spring.security.web.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;

@Configuration(proxyBeanMethods = false)
public class BeanConfiguration {

	@Bean
	public SpringSecurityDialect springSecurityDialect() {
		return new SpringSecurityDialect();
	}

	@Bean
	public LocalValidatorFactoryBean getValidator(MessageSource messageSource) {
		final var bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource);
		return bean;
	}
}
