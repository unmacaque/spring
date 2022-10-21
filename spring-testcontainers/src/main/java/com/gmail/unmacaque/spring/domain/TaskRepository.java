package com.gmail.unmacaque.spring.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "items", path = "items")
public interface TaskRepository extends CrudRepository<Task, Long> {}
