package com.gmail.unmacaque.spring.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.gmail.unmacaque.spring.domain.Book;
import com.gmail.unmacaque.spring.domain.Library;

@Controller
public class BooksController {

	private final Library library;

	public BooksController(Library library) {
		this.library = library;
	}

	@ModelAttribute("books")
	public List<Book> books() {
		return library.getBooks();
	}

	@GetMapping("/")
	public String listBooks() {
		return "books";
	}
}
