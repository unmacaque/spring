package com.gmail.unmacaque.spring.web

import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse

class HelloHandler(private val text: String) {

    fun sayHello(request: ServerRequest): ServerResponse = ServerResponse.ok().body("Hello $text")

}
