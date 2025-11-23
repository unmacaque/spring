package com.gmail.unmacaque.spring.cache.domain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

@Repository("library")
public class LibraryImpl implements Library {

	private static final Logger logger = LoggerFactory.getLogger(LibraryImpl.class);

	@Value("classpath:books.xml")
	private Resource resource;

	@Override
	@Cacheable("books")
	public List<Book> getBooks() {
		logger.info("reading {}", resource.getFilename());

		try {
			final var mapper = new XmlMapper().registerModule(new JavaTimeModule());
			return mapper.readValue(resource.getFile(), new TypeReference<>() {});
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
}
