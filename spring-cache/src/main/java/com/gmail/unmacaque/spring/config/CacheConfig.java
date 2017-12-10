package com.gmail.unmacaque.spring.config;

import java.util.List;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gmail.unmacaque.spring.domain.Book;
import com.thoughtworks.xstream.XStream;

@Configuration
@EnableCaching
public class CacheConfig {

	@Bean
	public XStream xstream() {
		XStream xstream = new XStream();
		XStream.setupDefaultSecurity(xstream);
		xstream.processAnnotations(Book.class);
		xstream.alias("catalog", List.class);
		xstream.allowTypes(new Class[] { Book.class });
		return xstream;
	}
}
