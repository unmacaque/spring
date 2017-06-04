package com.gmail.unmacaque.spring.domain

import org.springframework.data.repository.PagingAndSortingRepository

interface MessageRepository : PagingAndSortingRepository<Message, Long> {}
