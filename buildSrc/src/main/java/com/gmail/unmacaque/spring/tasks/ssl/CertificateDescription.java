package com.gmail.unmacaque.spring.tasks.ssl;

import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.provider.Property;

public abstract class CertificateDescription {

	public CertificateDescription() {
		getDays().convention(365);
	}

	public abstract RegularFileProperty getCert();

	public abstract RegularFileProperty getKey();

	public abstract Property<String> getSubject();

	public abstract Property<Integer> getDays();
}
