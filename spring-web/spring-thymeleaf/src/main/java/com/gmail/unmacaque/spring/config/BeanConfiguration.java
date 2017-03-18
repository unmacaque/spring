package com.gmail.unmacaque.spring.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

import com.gmail.unmacaque.spring.format.LocalDateTimeFormatter;

@Configuration
public class BeanConfiguration {
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
}
