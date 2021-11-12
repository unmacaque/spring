package com.gmail.unmacaque.spring.domain;

import java.time.ZonedDateTime;

public record ChatMessage(String author, ZonedDateTime postedOn, String text) {}
