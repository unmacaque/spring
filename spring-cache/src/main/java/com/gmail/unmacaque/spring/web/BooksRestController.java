package com.gmail.unmacaque.spring.web;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.unmacaque.spring.domain.Book;
import com.gmail.unmacaque.spring.domain.Library;

@RestController
public class BooksRestController {

	private final Library library;

	public BooksRestController(Library library) {
		this.library = library;
	}

	@GetMapping("/books")
	public List<Book> getBooks() throws IOException {
		return library.getBooks();
	}
}
