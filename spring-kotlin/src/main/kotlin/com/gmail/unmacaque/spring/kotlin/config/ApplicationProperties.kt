package com.gmail.unmacaque.spring.kotlin.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("app")
data class ApplicationProperties(
    /** Text to show **/
    val text: String = "World"
)
