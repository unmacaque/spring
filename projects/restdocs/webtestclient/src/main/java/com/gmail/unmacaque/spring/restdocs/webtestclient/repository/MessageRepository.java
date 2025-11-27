package com.gmail.unmacaque.spring.restdocs.webtestclient.repository;

import com.gmail.unmacaque.spring.restdocs.webtestclient.domain.Message;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MessageRepository extends ReactiveCrudRepository<Message, Long> {}
