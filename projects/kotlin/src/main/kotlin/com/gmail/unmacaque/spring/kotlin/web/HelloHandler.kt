package com.gmail.unmacaque.spring.kotlin.web

import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse

class HelloHandler(private val text: String) {

    fun sayHello(@Suppress("UNUSED_PARAMETER") request: ServerRequest): ServerResponse =
        ServerResponse.ok().body("Hello $text")

}
