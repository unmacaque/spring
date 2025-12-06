package com.gmail.unmacaque.spring.apiversioning.domain;

import java.time.LocalDate;

public record ProfileV2(
		String firstName,
		String lastName,
		String country,
		LocalDate dateOfBirth
) {}
