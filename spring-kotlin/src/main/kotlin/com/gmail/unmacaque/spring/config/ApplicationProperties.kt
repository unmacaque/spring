package com.gmail.unmacaque.spring.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("app")
data class ApplicationProperties(
    /** Text to show **/
    val text: String = "World"
)
