package com.gmail.unmacaque.spring.cache;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class BooksController {
	@Autowired
	private Library library;
	
	@ModelAttribute("books")
	public List<Book> getBooks() {
		try {
			return library.getBooks();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new ArrayList<Book>();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String listBooks() {
		return "books";
	}
}
