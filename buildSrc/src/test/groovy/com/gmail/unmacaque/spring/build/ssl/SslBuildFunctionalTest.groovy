package com.gmail.unmacaque.spring.build.ssl


import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.gradle.testkit.runner.UnexpectedBuildFailure
import spock.lang.Specification
import spock.lang.TempDir

class SslBuildFunctionalTest extends Specification {

    @TempDir
    File testProjectDir
    File settingsFile
    File buildFile

    def setup() {
        settingsFile = new File(testProjectDir, 'settings.gradle')
        buildFile = new File(testProjectDir, 'build.gradle')
    }

    def "generateSslCertificates creates certificates"() {
        given:
        settingsFile << "rootProject.name = 'ssl-test'"
        buildFile << """
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
        """

        when:
        def result = GradleRunner.create()
            .withProjectDir(testProjectDir)
            .withArguments('generateSslCertificates')
            .withPluginClasspath()
            .build()

        then:
        notThrown(UnexpectedBuildFailure)
        result.task(':generateSslCertificates').outcome == TaskOutcome.SUCCESS
    }
}

