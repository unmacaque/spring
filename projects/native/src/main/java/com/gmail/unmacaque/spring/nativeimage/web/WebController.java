package com.gmail.unmacaque.spring.nativeimage.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
public class WebController {

	private final Map<String, String> countryMap;

	public WebController(ResourceLoader resourceLoader, ObjectMapper objectMapper) throws IOException {
		final var resource = resourceLoader.getResource("classpath:iso-3166-1.json");
		final var countries = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<Country>>() {});
		countryMap = countries
				.stream()
				.collect(Collectors.toUnmodifiableMap(Country::code, Country::name));
	}

	@GetMapping("/")
	public ResponseEntity<Collection<String>> getCountryCodes() {
		return ResponseEntity.ok(countryMap.keySet());
	}

	@GetMapping("/{countryCode:[A-Z]{2}}")
	public ResponseEntity<String> getCountryName(@PathVariable String countryCode) {
		if (!countryMap.containsKey(countryCode)) {
			throw new NoSuchElementException();
		}
		return ResponseEntity.ok(countryMap.get(countryCode));
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<?> handleNoSuchElementException() {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
	}

	public record Country(@JsonProperty("Code") String code, @JsonProperty("Name") String name) {}

}
