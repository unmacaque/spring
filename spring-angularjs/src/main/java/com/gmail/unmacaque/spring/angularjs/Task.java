package com.gmail.unmacaque.spring.angularjs;

import java.time.LocalDateTime;

public class Task {

	private final int id;
	private final String title;
	private final LocalDateTime date;
	private final String text;
	private boolean done;

	public Task(int id, String title, LocalDateTime date, String text, boolean done) {
		this.id = id;
		this.title = title;
		this.date = date;
		this.text = text;
		this.done = done;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public String getText() {
		return text;
	}

	public boolean getDone() {
		return done;
	}
}
