package com.gmail.unmacaque.spring.build.ssl;

import org.bouncycastle.operator.OperatorCreationException;
import org.gradle.api.DefaultTask;
import org.gradle.api.file.DirectoryProperty;
import org.gradle.api.tasks.CacheableTask;
import org.gradle.api.tasks.OutputDirectory;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@CacheableTask
public abstract class SslTask extends DefaultTask {

	@OutputDirectory
	abstract DirectoryProperty getOutputDir();

	@TaskAction
	void run() {
		final var project = getProject();
		project.getPlugins().withType(SslPlugin.class, ssl -> {
			final var sslExtension = project.getExtensions().getByType(SslPluginExtension.class);
			final var outputDir = getOutputDir().get().getAsFile();
			outputDir.mkdirs();

			// CA Certificate
			final var caDescription = sslExtension.getCa();
			final var caKeyFile = new File(outputDir, caDescription.getKey().get().getAsFile().getName());
			final var caKey = createKey(caKeyFile);

			final String caCertSubject = createCaCertificate(outputDir, caDescription, caKey);

			// Certificates
			sslExtension.getCerts().all(certificateDescription -> {
				createCertificate(outputDir, caKey, caCertSubject, certificateDescription);
			});
		});
	}

	private static String createCaCertificate(File outputDir, CertificateDescription certificateDescription, KeyPair caKey) {
		final var caCertFile = new File(outputDir, certificateDescription.getCert().get().getAsFile().getName());
		final var caCertSubject = certificateDescription.getSubject().get();
		final var caCertDays = certificateDescription.getDays().get();
		final X509Certificate caCertificate;
		try {
			caCertificate = generateCaCertificate(caCertFile, caKey, caCertSubject, caCertDays);
		} catch (CertificateException | IOException | OperatorCreationException e) {
			throw new RuntimeException(e);
		}
		return caCertSubject;
	}

	private static void createCertificate(File outputDir, KeyPair caKey, String caCertSubject, CertificateDescription certificateDescription) {
		final var keyFile = new File(outputDir, certificateDescription.getKey().get().getAsFile().getName());
		final var key = createKey(keyFile);

		final var certFile = new File(outputDir, certificateDescription.getCert().get().getAsFile().getName());
		final var certSubject = certificateDescription.getSubject().get();
		final var certDays = certificateDescription.getDays().get();
		final X509Certificate certificate;
		try {
			certificate = generateCertificate(certFile, caKey.getPrivate(), caCertSubject, key, certSubject, certDays);
		} catch (CertificateException | IOException | OperatorCreationException | NoSuchProviderException e) {
			throw new RuntimeException(e);
		}
	}

	private static KeyPair createKey(File keyFile) {
		try {
			final KeyPair keyPair = Ssl.createKeyPair();
			final var keyContent = Ssl.convertToPem(keyPair);
			Files.writeString(keyFile.toPath(), keyContent);
			return keyPair;
		} catch (NoSuchAlgorithmException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static X509Certificate generateCaCertificate(File certFile, KeyPair keyPair, String subject, int days) throws CertificateException, OperatorCreationException, IOException {
		final var certificate = Ssl.createCaCertificate(keyPair, subject, days);
		final var certContent = Ssl.convertToPem(certificate);
		Files.writeString(certFile.toPath(), certContent);
		return certificate;
	}

	private static X509Certificate generateCertificate(File certFile, PrivateKey caPrivateKey, String caSubject, KeyPair keyPair, String subject, int days) throws CertificateException, OperatorCreationException, IOException, NoSuchProviderException {
		final var certificateRequest = Ssl.createCertificateRequest(keyPair, subject);
		final var certificate = Ssl.signCertificateRequest(certificateRequest, caPrivateKey, caSubject, keyPair, days);
		final var certContent = Ssl.convertToPem(certificate);
		Files.writeString(certFile.toPath(), certContent);
		return certificate;
	}
}
