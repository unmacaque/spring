package com.gmail.unmacaque.spring.kotlin.domain

import org.springframework.data.repository.CrudRepository

interface MessageRepository :
    CrudRepository<Message, Long>
