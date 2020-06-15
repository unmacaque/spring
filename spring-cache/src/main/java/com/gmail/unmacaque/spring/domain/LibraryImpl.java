package com.gmail.unmacaque.spring.domain;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Repository("library")
public class LibraryImpl implements Library {

	private static final Logger logger = LoggerFactory.getLogger(LibraryImpl.class);

	@Value("classpath:books.xml")
	private Resource resource;

	@Override
	@SuppressWarnings("unchecked")
	public List<Book> getBooks() {
		logger.info("reading {}", resource.getFilename());

		try {
			final var mapper = new XmlMapper();
			return mapper.readValue(resource.getFile(), List.class);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return Collections.emptyList();
		}
	}
}
