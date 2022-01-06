package com.gmail.unmacaque.spring.domain;

import java.time.Instant;

public record ChatMessage(String author, Instant postedOn, String text) {}
