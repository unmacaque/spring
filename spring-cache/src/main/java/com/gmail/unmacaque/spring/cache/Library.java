package com.gmail.unmacaque.spring.cache;

import java.io.IOException;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;

public interface Library {

	@Cacheable("books")
	public List<Book> getBooks() throws IOException;

}
