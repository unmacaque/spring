package com.gmail.unmacaque.spring.domain;

import java.time.LocalDate;
import java.util.StringJoiner;

public class Book {

	private String id;

	private String author;

	private String title;

	private String genre;

	private String price;

	private LocalDate publishDate;

	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public LocalDate getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		final StringJoiner joiner = new StringJoiner(",", "[", "]");
		joiner.add(id).add(author).add(publishDate.toString()).add(description).add(genre).add(price);
		return title + joiner;
	}
}
