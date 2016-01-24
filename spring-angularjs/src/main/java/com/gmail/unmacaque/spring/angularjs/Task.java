package com.gmail.unmacaque.spring.angularjs;

import java.time.LocalDateTime;

public class Task {

	private int id;
	private String title;
	private LocalDateTime date;
	private String text;
	private boolean done;

	public Task() {
	}

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

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
}
