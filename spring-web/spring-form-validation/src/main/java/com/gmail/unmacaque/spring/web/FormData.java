package com.gmail.unmacaque.spring.web;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

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
