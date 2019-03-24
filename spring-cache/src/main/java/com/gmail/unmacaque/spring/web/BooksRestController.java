package com.gmail.unmacaque.spring.web;

import com.gmail.unmacaque.spring.domain.Book;
import com.gmail.unmacaque.spring.domain.Library;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BooksRestController {

	private final Library library;

	public BooksRestController(Library library) {
		this.library = library;
	}

	@GetMapping("/books")
	public List<Book> getBooks() {
		return library.getBooks();
	}
}
