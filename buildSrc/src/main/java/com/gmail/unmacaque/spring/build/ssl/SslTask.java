package com.gmail.unmacaque.spring.build.ssl;

import org.bouncycastle.operator.OperatorCreationException;
import org.gradle.api.DefaultTask;
import org.gradle.api.file.Directory;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.SourceSetContainer;
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

public abstract class SslTask extends DefaultTask {

	@TaskAction
	void run() {
		final var project = getProject();
		project.getPlugins().withType(JavaPlugin.class, java -> {
			final var sourceSetContainer = project.getExtensions().getByType(SourceSetContainer.class);
			final var mainSourceSet = sourceSetContainer.getByName(SourceSet.MAIN_SOURCE_SET_NAME).getName();

			final var sslExtension = project.getExtensions().getByType(SslPluginExtension.class);
			final var resourcesDir = project.getLayout().getBuildDirectory().dir("resources/" + mainSourceSet).get();
			resourcesDir.getAsFile().mkdirs();

			// CA Certificate
			final var caDescription = sslExtension.getCa();
			final var caKeyFile = resourcesDir.file(caDescription.getKey().get());
			final var caKey = createKey(caKeyFile.getAsFile());

			final String caCertSubject = createCaCertificate(caDescription, resourcesDir, caKey);

			// Certificates
			sslExtension.getCerts().all(certificateDescription -> {
				createCertificate(resourcesDir, caKey, caCertSubject, certificateDescription);
			});
		});
	}

	private static String createCaCertificate(CertificateDescription certificateDescription, Directory resourcesDir, KeyPair caKey) {
		final var caCertFile = resourcesDir.file(certificateDescription.getCert().get());
		final var caCertSubject = certificateDescription.getSubject().get();
		final var caCertDays = certificateDescription.getDays().get();
		final X509Certificate caCertificate;
		try {
			caCertificate = generateCaCertificate(caCertFile.getAsFile(), caKey, caCertSubject, caCertDays);
		} catch (CertificateException | IOException | OperatorCreationException e) {
			throw new RuntimeException(e);
		}
		return caCertSubject;
	}

	private static void createCertificate(Directory output, KeyPair caKey, String caCertSubject, CertificateDescription certificateDescription) {
		final var keyFile = output.file(certificateDescription.getKey().get());
		final var key = createKey(keyFile.getAsFile());

		final var certFile = output.file(certificateDescription.getCert().get());
		final var certSubject = certificateDescription.getSubject().get();
		final var certDays = certificateDescription.getDays().get();
		final X509Certificate certificate;
		try {
			certificate = generateCertificate(certFile.getAsFile(), caKey.getPrivate(), caCertSubject, key, certSubject, certDays);
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
