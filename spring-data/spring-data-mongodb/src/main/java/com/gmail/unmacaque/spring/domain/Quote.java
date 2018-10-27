package com.gmail.unmacaque.spring.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Quote {

	@Id
	private String id;

	private String author;

	private String text;

	public String getText() {
		return text;
	}

	public String getId() {
		return id;
	}

	public String getAuthor() {
		return author;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}
