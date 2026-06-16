package com.gmail.unmacaque.spring.tasks.ssl;

public abstract class NamedCertificateDescription extends CertificateDescription {

	private final String name;

	public NamedCertificateDescription(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
