package com.gmail.unmacaque.spring.cache.web;

import com.gmail.unmacaque.spring.cache.domain.Book;
import com.gmail.unmacaque.spring.cache.domain.Library;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BooksRestController {

	private final Library library;

	public BooksRestController(Library library) {
		this.library = library;
	}

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Book> getBooks() {
		return library.getBooks();
	}
}
