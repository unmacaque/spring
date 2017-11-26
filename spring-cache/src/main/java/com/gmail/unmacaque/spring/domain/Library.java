package com.gmail.unmacaque.spring.domain;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

public interface Library {

	@Cacheable("books")
	List<Book> getBooks();

}
