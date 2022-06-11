package com.gmail.unmacaque.spring.build.ssl;

import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.OutputFile;

public abstract class CertificateDescription {

	@OutputFile
	public abstract RegularFileProperty getCert();

	@OutputFile
	public abstract RegularFileProperty getKey();

	@Input
	public abstract Property<String> getSubject();

	@Input
	public abstract Property<Integer> getDays();

	public CertificateDescription() {
		getDays().convention(365);
	}
}
