package com.gmail.unmacaque.spring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
public class Task implements Serializable {

	private static final long serialVersionUID = -2513462600168725588L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String title;

	@NotNull
	private ZonedDateTime date;

	private String text;

	private boolean done;

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public ZonedDateTime getDate() {
		return date;
	}

	public String getText() {
		return text;
	}

	public boolean getDone() {
		return done;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDate(ZonedDateTime date) {
		this.date = date;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
}
