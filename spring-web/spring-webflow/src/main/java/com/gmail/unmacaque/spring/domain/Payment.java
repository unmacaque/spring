package com.gmail.unmacaque.spring.domain;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class Payment implements Serializable {

	private static final long serialVersionUID = 4643233626429810563L;

	@NotNull
	private PaymentType type;

	public Payment(PaymentType type) {
		this.type = type;
	}

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}
}
