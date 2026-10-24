package com.gmail.unmacaque.spring.tasks.ssl;

import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
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
import org.bouncycastle.operator.bc.BcECContentSignerBuilder;
import org.bouncycastle.operator.bc.BcRSAContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;
import org.bouncycastle.util.io.pem.PemObject;

import javax.security.auth.x500.X500Principal;
import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.ECGenParameterSpec;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class Ssl {

	private static final String KEY_ALGORITHM_EC = "EC";

	private static final String EC_PARAM = "secp256r1";

	public static KeyPair generateKeyPair(String keyAlgorithm) {
		try {
			final KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance(keyAlgorithm);
			if (keyAlgorithm.equals(KEY_ALGORITHM_EC)) {
				keyGenerator.initialize(new ECGenParameterSpec(EC_PARAM));
			}
			return keyGenerator.generateKeyPair();
		} catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException e) {
			throw new RuntimeException(e);
		}
	}

	public static X509Certificate createCaCertificate(KeyPair keyPair, String subjectName, int days, String signatureAlgorithm) throws OperatorCreationException, CertificateException, CertIOException {
		final var now = Instant.now();
		final var notBefore = Date.from(now);
		final var notAfter = Date.from(now.plus(Duration.ofDays(days)));

		final var contentSigner = new JcaContentSignerBuilder(signatureAlgorithm).build(keyPair.getPrivate());
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

	public static PKCS10CertificationRequest createCertificateRequest(KeyPair keyPair, String subjectName, String signatureAlgorithm) throws OperatorCreationException {
		final var p10Builder = new JcaPKCS10CertificationRequestBuilder(
				new X500Principal(subjectName), keyPair.getPublic());

		final var builder = new JcaContentSignerBuilder(signatureAlgorithm);
		final var signer = builder.build(keyPair.getPrivate());
		return p10Builder.build(signer);
	}

	public static X509Certificate signCertificateRequest(
			PKCS10CertificationRequest signingRequest,
			PrivateKey caPrivateKey,
			X509Certificate caCertificate,
			KeyPair certificateKeyPair,
			int days,
			String keyAlgorithm,
			String signatureAlgorithm
	) throws OperatorCreationException, CertificateException, NoSuchProviderException, IOException {
		final var sigAlgId = new DefaultSignatureAlgorithmIdentifierFinder().find(signatureAlgorithm);
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

		final var contentSigner = switch (keyAlgorithm) {
			case KEY_ALGORITHM_EC -> new BcECContentSignerBuilder(sigAlgId, digAlgId);
			case "RSA" -> new BcRSAContentSignerBuilder(sigAlgId, digAlgId);
			default -> throw new RuntimeException("Unsupported key algorithm: " + keyAlgorithm);
		};
		final var holder = certificateBuilder.build(contentSigner.build(caKey));
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

	public static void writePrivateKey(File keyFile, PrivateKey privateKey) {
		try {
			final var keyContent = convertToPem(new PemObject("PRIVATE KEY", privateKey.getEncoded()));
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
		try (final var reader = new FileReader(keyFile);
			 final var pemParser = new PEMParser(reader)) {
			final var object = pemParser.readObject();
			if (object instanceof PEMKeyPair keyPair) {
				return converter.getKeyPair(keyPair).getPrivate();
			}
			if (object instanceof PrivateKeyInfo privateKeyInfo) {
				return converter.getPrivateKey(privateKeyInfo);
			}
			throw new IllegalArgumentException("Unsupported PEM object: " + (object == null ? "null" : object.getClass().getName()));
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
}
