package com.gmail.unmacaque.spring.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Bundle {
	private final String content;

	@JsonCreator
	public Bundle(@JsonProperty("content") String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		return content;
	}
}
