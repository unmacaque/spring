plugins {
    id("unmacaque.spring-boot")
    id("unmacaque.kotlin")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    runtimeOnly("com.h2database:h2")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
}

description = "Spring Kotlin"
