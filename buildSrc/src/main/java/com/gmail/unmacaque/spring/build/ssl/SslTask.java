package com.gmail.unmacaque.spring.build.ssl;

import org.bouncycastle.operator.OperatorCreationException;
import org.gradle.api.DefaultTask;
import org.gradle.api.file.DirectoryProperty;
import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.*;
import org.jspecify.annotations.NonNull;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;

public abstract class SslTask extends DefaultTask {

	@OutputDirectory
	abstract DirectoryProperty getOutputDir();

	@OutputFile
	abstract RegularFileProperty getCert();

	@OutputFile
	abstract RegularFileProperty getKey();

	@InputFile
	abstract RegularFileProperty getSigningCert();

	@InputFile
	abstract RegularFileProperty getSigningKey();

	@Input
	abstract Property<@NonNull String> getSubject();

	@Input
	abstract Property<@NonNull Integer> getDays();

	@TaskAction
	void run() {
		final var outputDir = getOutputDir().get().getAsFile();
		try {
			Files.createDirectories(outputDir.toPath());
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}

		final var caKey = Ssl.readPrivateKey(getSigningKey().get().getAsFile());
		final var caCert = Ssl.readCertificate(getSigningCert().get().getAsFile());

		final var keyFile = new File(outputDir, getKey().get().getAsFile().getName());
		final var key = Ssl.generateKeyPair();
		Ssl.writeKeyPair(keyFile, key);

		final var certFile = new File(outputDir, getCert().get().getAsFile().getName());
		final var subject = getSubject().get();
		final var days = getDays().get();
		try {
			final var certificateRequest = Ssl.createCertificateRequest(key, subject);
			final var certificate = Ssl.signCertificateRequest(certificateRequest, caKey, caCert, key, days);
			final var certContent = Ssl.convertToPem(certificate);
			Files.writeString(certFile.toPath(), certContent);
		} catch (CertificateException | IOException | OperatorCreationException | NoSuchProviderException e) {
			throw new RuntimeException(e);
		}
	}
}
