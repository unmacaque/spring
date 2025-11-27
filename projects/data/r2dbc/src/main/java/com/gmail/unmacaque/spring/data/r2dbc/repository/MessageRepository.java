package com.gmail.unmacaque.spring.data.r2dbc.repository;

import com.gmail.unmacaque.spring.data.r2dbc.domain.Message;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MessageRepository extends ReactiveCrudRepository<Message, Long> {}
