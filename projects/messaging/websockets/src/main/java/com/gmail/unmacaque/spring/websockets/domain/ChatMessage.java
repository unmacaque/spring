package com.gmail.unmacaque.spring.websockets.domain;

import java.time.Instant;

public record ChatMessage(String author, Instant postedOn, String text) {}
