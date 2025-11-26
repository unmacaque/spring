package com.gmail.unmacaque.spring.cache.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import tools.jackson.core.type.TypeReference;
import tools.jackson.dataformat.xml.XmlMapper;

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
			final var mapper = XmlMapper.builder().build();
			return mapper.readValue(resource.getFile(), new TypeReference<>() {});
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
}
