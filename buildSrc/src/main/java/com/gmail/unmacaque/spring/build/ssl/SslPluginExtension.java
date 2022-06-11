package com.gmail.unmacaque.spring.build.ssl;

import org.gradle.api.Action;
import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.tasks.Nested;

public interface SslPluginExtension {

	@Nested
	CertificateDescription getCa();

	NamedDomainObjectContainer<NamedCertificateDescription> getCerts();

	default void ca(Action<? super CertificateDescription> action) {
		action.execute(getCa());
	}
}
