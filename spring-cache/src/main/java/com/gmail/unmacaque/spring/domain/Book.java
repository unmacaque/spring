package com.gmail.unmacaque.spring.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record Book(
		String id,
		String author,
		String title,
		String genre,
		String price,
		@JsonProperty("publish-date") LocalDate publishDate,
		String description
) {}
