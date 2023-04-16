package com.gmail.unmacaque.spring.build.ssl;

import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.provider.Property;

public abstract class CertificateDescription {

	public abstract RegularFileProperty getCert();

	public abstract RegularFileProperty getKey();

	public abstract Property<String> getSubject();

	public abstract Property<Integer> getDays();

	public CertificateDescription() {
		getDays().convention(365);
	}
}
