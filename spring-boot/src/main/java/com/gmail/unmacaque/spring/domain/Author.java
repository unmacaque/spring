package com.gmail.unmacaque.spring.domain;

public class Author {

	private final String name;

	private final String mail;

	public Author(String name, String mail) {
		this.name = name;
		this.mail = mail;
	}

	public String getName() {
		return name;
	}

	public String getMail() {
		return mail;
	}
}
