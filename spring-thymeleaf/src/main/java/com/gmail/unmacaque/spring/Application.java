package com.gmail.unmacaque.spring;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

import com.gmail.unmacaque.spring.thymeleaf.format.LocalDateTimeFormatter;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	@Bean
	public FormattingConversionServiceFactoryBean conversionService() {
		FormattingConversionServiceFactoryBean conversionService = new FormattingConversionServiceFactoryBean();
		conversionService.setFormatters(Collections.singleton(localDateTimeFormatter()));
		return conversionService;
	}

	@Bean
	public LocalDateTimeFormatter localDateTimeFormatter() {
		return new LocalDateTimeFormatter();
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
