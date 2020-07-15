package com.gmail.unmacaque.spring.domain;

import java.util.List;

public class MyBeanImpl implements MyBean {

	private final String myString;

	private final List<String> myList;

	private final Author author;

	public MyBeanImpl(String myString, List<String> myList, String name, String mail) {
		this.myString = myString;
		this.myList = myList;
		this.author = new Author(name, mail);
	}

	@Override
	public String getString() {
		return myString;
	}

	@Override
	public List<String> getList() {
		return myList;
	}

	@Override
	public Author getAuthor() {
		return author;
	}
}
