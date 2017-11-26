package com.gmail.unmacaque.spring.autoconfigure;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties("application")
@Validated
public class MyBeanProperties {
	/**
	 * Single value property. Will be visible on standard output.
	 */
	@NotBlank
	private String myProperty;

	/**
	 * List property. Will be visible on standard output.
	 */
	@NotNull
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
