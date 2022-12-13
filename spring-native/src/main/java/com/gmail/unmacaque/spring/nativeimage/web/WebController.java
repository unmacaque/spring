package com.gmail.unmacaque.spring.nativeimage.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;
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

	@GetMapping("/{countryCode}")
	public ResponseEntity<String> getCountryName(@PathVariable String countryCode) {
		if (!countryMap.containsKey(countryCode)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(countryMap.get(countryCode));
	}

	public record Country(@JsonProperty("Code") String code, @JsonProperty("Name") String name) {}

}
