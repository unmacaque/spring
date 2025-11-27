package com.gmail.unmacaque.spring.build.ssl;

import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.provider.Property;
import org.jspecify.annotations.NonNull;

public abstract class CertificateDescription {

	public CertificateDescription() {
		getDays().convention(365);
	}

	public abstract RegularFileProperty getCert();

	public abstract RegularFileProperty getKey();

	public abstract Property<@NonNull String> getSubject();

	public abstract Property<@NonNull Integer> getDays();
}
