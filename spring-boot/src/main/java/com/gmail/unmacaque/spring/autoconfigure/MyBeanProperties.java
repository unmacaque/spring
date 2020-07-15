package com.gmail.unmacaque.spring.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@ConstructorBinding
@ConfigurationProperties("application")
@Validated
public class MyBeanProperties {
	/**
	 * Single value property. Will be visible on standard output.
	 */
	@NotEmpty
	private final String myProperty;

	/**
	 * List property. Will be visible on standard output.
	 */
	@NotNull
	private final List<String> myList;

	private final NestedProperties author;

	public MyBeanProperties(String myProperty, List<String> myList, @DefaultValue NestedProperties author) {
		this.myProperty = myProperty;
		this.myList = myList;
		this.author = author;
	}

	public String getMyProperty() {
		return myProperty;
	}

	public List<String> getMyList() {
		return myList;
	}

	public NestedProperties getAuthor() {
		return author;
	}

	@ConstructorBinding
	@Validated
	public static class NestedProperties {

		@NotEmpty
		private final String name;

		@Email
		private final String mail;

		public NestedProperties(String name, String mail) {
			this.name = name;
			this.mail = mail;
		}

		public String getName() {
			return name;
		}

		public String getMail() {
			return mail;
		}
	}
}
