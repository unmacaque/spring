package com.gmail.unmacaque.spring.tasks.ssl;

import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.provider.Property;

public abstract class CertificateDescription {

	public CertificateDescription() {
		getDays().convention(365);
		getKeyAlgorithm().convention("EC");
		getSignatureAlgorithm().convention("SHA256withECDSA");
	}

	public abstract RegularFileProperty getCert();

	public abstract RegularFileProperty getKey();

	public abstract Property<String> getSubject();

	public abstract Property<Integer> getDays();

	public abstract Property<String> getKeyAlgorithm();

	public abstract Property<String> getSignatureAlgorithm();
}
