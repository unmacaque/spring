package com.gmail.unmacaque.spring.cache.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record Book(
		String id,
		String author,
		String title,
		String genre,
		String price,
		@JsonProperty("publish-date") Instant publishDate,
		String description
) {}
