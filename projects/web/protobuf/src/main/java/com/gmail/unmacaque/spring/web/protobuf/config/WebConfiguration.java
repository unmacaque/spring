package com.gmail.unmacaque.spring.web.protobuf.config;

import com.google.protobuf.util.JsonFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.protobuf.ProtobufJsonFormatHttpMessageConverter;

@Configuration(proxyBeanMethods = false)
public class WebConfiguration {

	@Bean
	ProtobufJsonFormatHttpMessageConverter protobufJsonFormatHttpMessageConverter() {
		return new ProtobufJsonFormatHttpMessageConverter(JsonFormat.parser(), JsonFormat.printer());
	}

}
