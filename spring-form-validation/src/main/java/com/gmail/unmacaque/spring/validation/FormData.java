package com.gmail.unmacaque.spring.validation;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

public class FormData {
	@Length(min = 3, max = 5)
	private String shortName;
	
	@Min(10)
	@Max(10000)
	private String shortInteger;
	
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String comment;

	public String getShortName() {
		return shortName;
	}

	public String getShortInteger() {
		return shortInteger;
	}

	public String getComment() {
		return comment;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public void setShortInteger(String shortInteger) {
		this.shortInteger = shortInteger;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}