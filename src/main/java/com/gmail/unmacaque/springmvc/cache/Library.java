package com.gmail.unmacaque.springmvc.cache;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.thoughtworks.xstream.XStream;

@Component("library")
public class Library {

	private static final Logger logger = LogManager.getLogger();

	@Value("classpath:books.xml")
	private Resource resource;

	@SuppressWarnings("unchecked")
	@Cacheable("books")
	public List<Book> getBooks() throws IOException {
		XStream xstream = new XStream();
		xstream.alias("catalog", List.class);
		xstream.alias("book", Book.class);

		logger.info("reading {}", resource.getFilename());

		List<Book> catalog = (List<Book>) xstream.fromXML(resource.getFile());

		return catalog;
	}
}
