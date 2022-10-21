package com.gmail.unmacaque.spring.domain

import org.springframework.data.repository.CrudRepository

interface MessageRepository :
    CrudRepository<Message, Long>
