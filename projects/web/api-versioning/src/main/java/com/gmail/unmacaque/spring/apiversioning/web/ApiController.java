package com.gmail.unmacaque.spring.apiversioning.web;

import com.gmail.unmacaque.spring.apiversioning.domain.ProfileV1;
import com.gmail.unmacaque.spring.apiversioning.domain.ProfileV2;
import com.gmail.unmacaque.spring.apiversioning.domain.ProfileV3;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/*/profile")
public class ApiController {

	@GetMapping
	public ProfileV1 profile() {
		return new ProfileV1("John", "Doe", "Fiji");
	}

	@GetMapping(version = "2")
	public ProfileV2 profileV2() {
		return new ProfileV2("John", "Doe", "Fiji", LocalDate.of(1984, 7, 15));
	}

	@GetMapping(version = "3")
	public ProfileV3 profileV3() {
		return new ProfileV3("John", "Doe", "Fiji", LocalDate.of(1984, 7, 15), List.of("Climbing", "Programming"));
	}
}
