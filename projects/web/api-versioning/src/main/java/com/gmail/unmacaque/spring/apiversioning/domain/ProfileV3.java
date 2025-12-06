package com.gmail.unmacaque.spring.apiversioning.domain;

import java.time.LocalDate;
import java.util.Collection;

public record ProfileV3(
		String firstName,
		String lastName,
		String country,
		LocalDate dateOfBirth,
		Collection<String> interests
) {}
