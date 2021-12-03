plugins {
    `kotlin-dsl`
    id("groovy-gradle-plugin")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.0")
    implementation("org.jetbrains.kotlin:kotlin-allopen")
    implementation("org.jetbrains.kotlin:kotlin-noarg")
    implementation("org.springframework.boot:spring-boot-gradle-plugin:2.6.1")
    implementation("io.spring.gradle:dependency-management-plugin:1.0.11.RELEASE")
    implementation("com.github.node-gradle:gradle-node-plugin:3.1.0")
    implementation("com.palantir.gradle.docker:gradle-docker:0.30.0")
    implementation("com.google.protobuf:protobuf-gradle-plugin:0.8.17")
    implementation("com.gorylenko.gradle-git-properties:gradle-git-properties:2.3.2")
    implementation("org.asciidoctor:asciidoctor-gradle-jvm:3.3.2")
    implementation("org.springdoc:springdoc-openapi-gradle-plugin:1.3.3")
    implementation("gradle.plugin.com.github.jengelman.gradle.plugins:gradle-processes:0.5.0")
}

repositories {
    mavenLocal()
    gradlePluginPortal()
}
