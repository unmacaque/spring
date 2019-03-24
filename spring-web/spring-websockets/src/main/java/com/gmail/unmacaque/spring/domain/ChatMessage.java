package com.gmail.unmacaque.spring.domain;

import java.time.ZonedDateTime;

public class ChatMessage {

	private String author;

	private ZonedDateTime postedOn;

	private String text;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public ZonedDateTime getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(ZonedDateTime postedOn) {
		this.postedOn = postedOn;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
