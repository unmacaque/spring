package com.gmail.unmacaque.spring.data.r2dbc.domain;

import org.springframework.data.annotation.Id;

public class Message {

	@Id
	private Long id;

	private String title;

	private String author;

	private String content;

	public String getContent() {
		return content;
	}

	public Long getId() {
		return id;
	}

	public String getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
