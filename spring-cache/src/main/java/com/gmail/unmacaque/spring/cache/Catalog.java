package com.gmail.unmacaque.spring.cache;

import java.util.List;

public class Catalog {
	private List<Book> book;

	public List<Book> getBooks() {
		return book;
	}

	public void setBooks(List<Book> book) {
		this.book = book;
	}
}
