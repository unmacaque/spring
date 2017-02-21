package com.gmail.unmacaque.spring;

import java.io.FileNotFoundException;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application implements ApplicationRunner {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	private ClassPathResource keyStoreResource = new ClassPathResource("client.p12");

	private ClassPathResource trustStoreResource = new ClassPathResource("trust.jks");

	@Autowired
	private RestTemplate restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public RestTemplate restTemplate()
			throws NoSuchAlgorithmException, CertificateException, FileNotFoundException, IOException, KeyStoreException,
			KeyManagementException, UnrecoverableKeyException {

		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		keyStore.load(keyStoreResource.getInputStream(), "storepass".toCharArray());

		KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
		trustStore.load(trustStoreResource.getInputStream(), "trustpass".toCharArray());

		HttpClient httpClient = HttpClients.custom().setSSLContext(SSLContextBuilder.create()
				.loadTrustMaterial(trustStore, null)
				.loadKeyMaterial(keyStore, "storepass".toCharArray())
				.build()).build();
		ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		return new RestTemplate(requestFactory);
	}

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		String responseText = restTemplate.getForObject("https://localhost:8443", String.class);
		logger.info(responseText);
	}
}
