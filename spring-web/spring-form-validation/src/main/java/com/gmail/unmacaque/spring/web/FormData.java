package com.gmail.unmacaque.spring.web;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public class FormData {
	@NotEmpty
	@Length(min = 3)
	private String name;

	@NotEmpty
	@Email
	private String mail;

	@Min(10)
	@Max(99)
	private int age;

	@Length(max = 200)
	private String comment;

	public String getName() {
		return name;
	}

	public String getMail() {
		return mail;
	}

	public int getAge() {
		return age;
	}

	public String getComment() {
		return comment;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
