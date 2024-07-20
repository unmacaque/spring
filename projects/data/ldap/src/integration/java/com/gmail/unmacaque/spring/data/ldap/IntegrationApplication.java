package com.gmail.unmacaque.spring.data.ldap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

public class IntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.from(Application::main).with(IntegrationApplicationConfiguration.class).run(args);
	}

	@TestConfiguration(proxyBeanMethods = false)
	static class IntegrationApplicationConfiguration {

		@SuppressWarnings("resource")
		@Bean
		@ServiceConnection(name = "osixia/openldap")
		public GenericContainer<?> openldapContainer() {
			return new GenericContainer<>(DockerImageName.parse("osixia/openldap"))
					.withEnv("LDAP_DOMAIN", "springframework.org")
					.withEnv("LDAP_TLS", "false")
					.withExposedPorts(389)
					.withCommand("--loglevel debug")
					.withCopyFileToContainer(MountableFile.forClasspathResource("directory.ldif"),
							"/container/service/slapd/assets/config/bootstrap/ldif/custom/directory.ldif");
		}
	}
}
