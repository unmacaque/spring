package com.gmail.unmacaque.spring.build.ssl;

import org.gradle.testkit.runner.BuildTask;
import org.gradle.testkit.runner.GradleRunner;
import org.gradle.testkit.runner.TaskOutcome;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;

class SslBuildFunctionalTest {

	private File testProjectDir;

	private File buildFile;

	private static void assertTaskOutcomeSuccess(BuildTask buildTask) {
		assertThat(buildTask.getOutcome()).isEqualTo(TaskOutcome.SUCCESS);
	}

	@BeforeEach
	void setup(@TempDir File testProjectDir) {
		this.testProjectDir = testProjectDir;
		this.buildFile = new File(this.testProjectDir, "build.gradle");
	}

	@Test
	@DisplayName("generateSslCertificates creates certificates")
	void generateSslCertificates() throws IOException {
		Files.writeString(buildFile.toPath(), """
				plugins {
				    id 'unmacaque.ssl'
				}
				
				ssl {
				    outputDir = file('out')
				    ca {
				        cert = file('ca.pem')
				        key = file('ca.key')
				        subject = 'CN=root CA'
				    }
				}
				""".stripIndent());

		final var result = GradleRunner.create()
				.withProjectDir(testProjectDir)
				.withArguments("generateSslCertificates")
				.withPluginClasspath()
				.build();

		assertThat(result.getTasks())
				.allSatisfy(SslBuildFunctionalTest::assertTaskOutcomeSuccess)
				.map(BuildTask::getPath)
				.contains(":generateSslCertificates", ":generateSslCertificatesCa");
	}

	@Test
	@DisplayName("generateSslCertificates is attached to processResources task of a java project")
	void testPluginIsDependencyOfProcessResourcesTasks() throws IOException {
		Files.writeString(buildFile.toPath(), """
				plugins {
				    id 'java'
				    id 'unmacaque.ssl'
				}
				
				ssl {
				    outputDir = file('out')
				    ca {
				        cert = file('ca.pem')
				        key = file('ca.key')
				        subject = 'CN=root CA'
				    }
				}
				""".stripIndent());

		final var result = GradleRunner.create()
				.withProjectDir(testProjectDir)
				.withArguments("check")
				.withPluginClasspath()
				.build();

		assertThat(result.task(":generateSslCertificates")).satisfies(SslBuildFunctionalTest::assertTaskOutcomeSuccess);
		assertThat(result.task(":generateSslCertificatesCa")).satisfies(SslBuildFunctionalTest::assertTaskOutcomeSuccess);
	}
}

