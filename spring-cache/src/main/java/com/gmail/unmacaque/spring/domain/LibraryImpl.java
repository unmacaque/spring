package com.gmail.unmacaque.spring.domain;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import com.thoughtworks.xstream.XStream;

@Repository("library")
public class LibraryImpl implements Library {

	private static final Logger logger = LoggerFactory.getLogger(LibraryImpl.class);

	@Value("classpath:books.xml")
	private Resource resource;

	@Override
	@SuppressWarnings("unchecked")
	public List<Book> getBooks() throws IOException {
		XStream xstream = new XStream();
		xstream.alias("catalog", List.class);
		xstream.alias("book", Book.class);

		logger.info("reading {}", resource.getFilename());

		List<Book> catalog = (List<Book>) xstream.fromXML(resource.getFile());

		return catalog;
	}
}
