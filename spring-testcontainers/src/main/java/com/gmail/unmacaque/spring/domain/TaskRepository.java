package com.gmail.unmacaque.spring.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "items", path = "items")
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {
}
