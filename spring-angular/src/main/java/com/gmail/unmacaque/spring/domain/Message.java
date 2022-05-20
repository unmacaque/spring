package com.gmail.unmacaque.spring.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

public class Message {

	@Id
	private Long id;

	private String title;

	private String author;

	private String content;

	@CreatedDate
	private Instant createdDate;

	@LastModifiedDate
	private Instant lastModifiedDate;

	public String getContent() {
		return content;
	}

	public Instant getCreatedDate() {
		return createdDate;
	}

	public Long getId() {
		return id;
	}

	public Instant getLastModifiedDate() {
		return lastModifiedDate;
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

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLastModifiedDate(Instant lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public static Message of(String title, String author, String content) {
		final var message = new Message();
		message.title = title;
		message.author = author;
		message.content = content;
		return message;
	}
}
