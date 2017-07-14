package com.gmail.unmacaque.spring.config;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SecurityConfiguration {

	private ClassPathResource keyStoreResource = new ClassPathResource("client.p12");

	private ClassPathResource trustStoreResource = new ClassPathResource("trust.jks");

	@Bean
	@ConditionalOnResource(resources = "classpath:client.p12")
	public RestTemplate restTemplate()
			throws NoSuchAlgorithmException, CertificateException, IOException, KeyStoreException,
			KeyManagementException, UnrecoverableKeyException {

		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		keyStore.load(keyStoreResource.getInputStream(), "storepass".toCharArray());

		KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
		trustStore.load(trustStoreResource.getInputStream(), "trustpass".toCharArray());

		HttpClient httpClient = HttpClients.custom()
				.setSSLContext(SSLContextBuilder.create()
						.loadTrustMaterial(trustStore, null)
						.loadKeyMaterial(keyStore, "storepass".toCharArray())
						.build())
				.build();
		ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		return new RestTemplate(requestFactory);
	}

	@Bean
	@ConditionalOnMissingBean
	public RestTemplate restTemplateNoSsl() {
		return new RestTemplate();
	}
}
