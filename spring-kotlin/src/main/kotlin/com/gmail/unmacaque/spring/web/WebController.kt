package com.gmail.unmacaque.spring.web

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import com.gmail.unmacaque.spring.domain.HelloService

@RestController
class WebController(val helloService : HelloService) {

    @GetMapping("/")
    fun hello() = helloService.sayHello("World")

    @GetMapping("/{name}")
    fun helloName(@PathVariable name : String) = helloService.sayHello(name);
 
}