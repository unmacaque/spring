package com.gmail.unmacaque.spring.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Greeting {
	private String name;

	private LocalDateTime time;

	public Greeting() {
	}

	public Greeting(String name, LocalDateTime time) {
		this.name = Objects.requireNonNull(name);
		this.time = Objects.requireNonNull(time);
	}

	public String getName() {
		return name;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}
}
