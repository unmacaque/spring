package com.gmail.unmacaque.spring.cache;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class BooksController {

	private static final Logger logger = LogManager.getLogger();

	@Autowired
	private Library library;
	
	@ModelAttribute("books")
	public List<Book> getBooks() {
		try {
			return library.getBooks();
		} catch (IOException e) {
			logger.log(Level.ERROR, e.getMessage(), e);
		}
		
		return Collections.emptyList();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String listBooks() {
		return "books";
	}
}
