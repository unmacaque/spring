package com.gmail.unmacaque.spring.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application")
public class BootApplicationProperties {
	/**
	 * Single value property. Will be printed to standard output.
	 */
	private String myProperty;

	/**
	 * List property. Will be printed to standard output.
	 */
	private final List<String> myList = new ArrayList<>();

	public String getMyProperty() {
		return myProperty;
	}

	public void setMyProperty(String myProperty) {
		this.myProperty = myProperty;
	}

	public List<String> getMyList() {
		return myList;
	}
}
