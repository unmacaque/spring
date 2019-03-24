package com.gmail.unmacaque.spring.domain;

import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface Library {

	@Cacheable("books")
	List<Book> getBooks();

}
