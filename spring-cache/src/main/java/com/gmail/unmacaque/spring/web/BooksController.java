package com.gmail.unmacaque.spring.web;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.gmail.unmacaque.spring.domain.Book;
import com.gmail.unmacaque.spring.domain.Library;

@Controller
public class BooksController {

	private static final Logger logger = LoggerFactory.getLogger(BooksController.class);

	@Autowired
	private Library library;

	@ModelAttribute("books")
	public List<Book> books() {
		try {
			return library.getBooks();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

		return Collections.emptyList();
	}

	@GetMapping("/")
	public String listBooks() {
		return "books";
	}
}
