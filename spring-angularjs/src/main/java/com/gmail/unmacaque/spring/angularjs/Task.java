package com.gmail.unmacaque.spring.angularjs;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TASKS")
public class Task implements Serializable {

	private static final long serialVersionUID = -2513462600168725588L;

	private int id;
	private String title;
	private LocalDateTime date;
	private String text;
	private boolean done;

	public Task() {}

	public Task(int id, String title, LocalDateTime date, String text, boolean done) {
		this.id = id;
		this.title = title;
		this.date = date;
		this.text = text;
		this.done = done;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}

	@Column(name = "DATE")
	public LocalDateTime getDate() {
		return date;
	}

	@Column(name = "TEXT")
	public String getText() {
		return text;
	}

	@Column(name = "DONE")
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
