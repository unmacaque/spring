package com.gmail.unmacaque.spring.build.ssl;

import org.bouncycastle.operator.OperatorCreationException;
import org.gradle.api.DefaultTask;
import org.gradle.api.file.DirectoryProperty;
import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.TaskAction;
import org.jspecify.annotations.NonNull;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.security.cert.CertificateException;

public abstract class SslCaTask extends DefaultTask {

	@OutputDirectory
	abstract DirectoryProperty getOutputDir();

	@OutputFile
	abstract RegularFileProperty getCert();

	@OutputFile
	abstract RegularFileProperty getKey();

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

		final var caKeyFile = new File(outputDir, getKey().get().getAsFile().getName());
		final var caKey = Ssl.generateKeyPair();
		Ssl.writeKeyPair(caKeyFile, caKey);

		final var certFile = new File(outputDir, getCert().get().getAsFile().getName());
		final var caCertSubject = getSubject().get();
		final var caCertDays = getDays().get();
		try {
			final var certificate = Ssl.createCaCertificate(caKey, caCertSubject, caCertDays);
			final var certContent = Ssl.convertToPem(certificate);
			Files.writeString(certFile.toPath(), certContent);
		} catch (CertificateException | IOException | OperatorCreationException e) {
			throw new RuntimeException(e);
		}
	}
}
