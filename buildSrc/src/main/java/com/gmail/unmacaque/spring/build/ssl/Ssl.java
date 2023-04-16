package com.gmail.unmacaque.spring.build.ssl;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.crypto.util.PrivateKeyFactory;
import org.bouncycastle.jce.PrincipalUtil;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DefaultSignatureAlgorithmIdentifierFinder;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.bc.BcRSAContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;

import javax.security.auth.x500.X500Principal;
import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class Ssl {

	public static final String KEY_ALGORITHM = "RSA";

	public static final String SIGNATURE_ALGORITHM = "SHA256withRSA";

	public static KeyPair generateKeyPair() {
		try {
			final KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
			return keyGenerator.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public static X509Certificate createCaCertificate(KeyPair keyPair, String subjectName, int days) throws OperatorCreationException, CertificateException, CertIOException {
		final var now = Instant.now();
		final var notBefore = Date.from(now);
		final var notAfter = Date.from(now.plus(Duration.ofDays(days)));

		final var contentSigner = new JcaContentSignerBuilder(SIGNATURE_ALGORITHM).build(keyPair.getPrivate());
		final var x500Name = new X500Name(subjectName);
		final var certificateBuilder =
				new JcaX509v3CertificateBuilder(
						x500Name,
						BigInteger.ONE,
						notBefore,
						notAfter,
						x500Name,
						keyPair.getPublic()
				)
						.addExtension(Extension.basicConstraints, true, new BasicConstraints(true));
		final var certificateHolder = certificateBuilder.build(contentSigner);
		final var converter = new JcaX509CertificateConverter();
		return converter.getCertificate(certificateHolder);
	}

	public static PKCS10CertificationRequest createCertificateRequest(KeyPair keyPair, String subjectName) throws OperatorCreationException {
		final var p10Builder = new JcaPKCS10CertificationRequestBuilder(
				new X500Principal(subjectName), keyPair.getPublic());

		final var builder = new JcaContentSignerBuilder(SIGNATURE_ALGORITHM);
		final var signer = builder.build(keyPair.getPrivate());
		return p10Builder.build(signer);
	}

	public static X509Certificate signCertificateRequest(
			PKCS10CertificationRequest signingRequest,
			PrivateKey caPrivateKey,
			X509Certificate caCertificate,
			KeyPair certificateKeyPair,
			int days
	) throws OperatorCreationException, CertificateException, NoSuchProviderException, IOException {
		final var sigAlgId = new DefaultSignatureAlgorithmIdentifierFinder().find(SIGNATURE_ALGORITHM);
		final var digAlgId = new DefaultDigestAlgorithmIdentifierFinder().find(sigAlgId);

		final var caKey = PrivateKeyFactory.createKey(caPrivateKey.getEncoded());
		final var keyInfo = SubjectPublicKeyInfo.getInstance(certificateKeyPair.getPublic().getEncoded());

		final var now = Instant.now();
		final var notBefore = Date.from(now);
		final var notAfter = Date.from(now.plus(Duration.ofDays(days)));

		final var certificateBuilder = new X509v3CertificateBuilder(
				new X500Name(PrincipalUtil.getIssuerX509Principal(caCertificate).getName()),
				BigInteger.ONE,
				notBefore,
				notAfter,
				signingRequest.getSubject(),
				keyInfo
		);

		final var contentSigner = new BcRSAContentSignerBuilder(sigAlgId, digAlgId).build(caKey);
		final var holder = certificateBuilder.build(contentSigner);
		final var certificate = holder.toASN1Structure();
		final var certificateFactory = CertificateFactory.getInstance("X.509");

		try (final var bais = new ByteArrayInputStream(certificate.getEncoded())) {
			return (X509Certificate) (certificateFactory.generateCertificate(bais));
		}
	}

	public static String convertToPem(Object object) throws IOException {
		final var stringWriter = new StringWriter();
		try (final var pemWriter = new JcaPEMWriter(stringWriter)) {
			pemWriter.writeObject(object);
		}
		return stringWriter.toString();
	}

	public static void writeKeyPair(File keyFile, KeyPair keyPair) {
		try {
			final var keyContent = convertToPem(keyPair);
			Files.writeString(keyFile.toPath(), keyContent);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	public static X509Certificate readCertificate(File certificateFile) {
		try {
			final var factory = CertificateFactory.getInstance("X.509");
			return (X509Certificate) factory.generateCertificate(new FileInputStream(certificateFile));
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		} catch (CertificateException e) {
			throw new RuntimeException(e);
		}
	}

	public static PrivateKey readPrivateKey(File keyFile) {
		final var converter = new JcaPEMKeyConverter();
		try (final var pemParser = new PEMParser(new FileReader(keyFile))) {
			final var object = pemParser.readObject();
			final var keyPair = (PEMKeyPair) object;
			return converter.getPrivateKey(keyPair.getPrivateKeyInfo());
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
}
