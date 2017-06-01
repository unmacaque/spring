package com.gmail.unmacaque.spring.domain

import org.springframework.stereotype.Service

@Service
interface HelloService {
    fun sayHello(name: String) : String
}