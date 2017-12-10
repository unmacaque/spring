package com.gmail.unmacaque.spring.domain;

import java.io.IOException;
import java.util.Collections;
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

	private final XStream xstream;

	public LibraryImpl(XStream xstream) {
		this.xstream = xstream;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Book> getBooks() {
		logger.info("reading {}", resource.getFilename());

		try {
			return (List<Book>) xstream.fromXML(resource.getFile());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return Collections.emptyList();
		}
	}
}
