package com.gmail.unmacaque.spring.build.ssl;

import org.gradle.api.Action;
import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.file.DirectoryProperty;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.Nested;
import org.jspecify.annotations.NonNull;

public interface SslPluginExtension {

	DirectoryProperty getOutputDir();

	@Nested
	CertificateDescription getCa();

	@Input
	NamedDomainObjectContainer<@NonNull NamedCertificateDescription> getCerts();

	default void ca(Action<? super CertificateDescription> action) {
		action.execute(getCa());
	}

	default void certs(Action<? super NamedDomainObjectContainer<@NonNull NamedCertificateDescription>> action) {
		action.execute(getCerts());
	}
}
