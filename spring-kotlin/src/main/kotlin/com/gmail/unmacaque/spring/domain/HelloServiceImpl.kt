package com.gmail.unmacaque.spring.domain

class HelloServiceImpl : HelloService {
    override fun sayHello(name: String): String = "Hello " + name
}