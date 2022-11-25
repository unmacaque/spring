package com.gmail.unmacaque.spring.data.ldap.domain;

import org.springframework.data.ldap.repository.LdapRepository;

import java.util.Optional;

public interface PersonRepository extends LdapRepository<Person> {

	Optional<Person> findByUid(String uid);

	Iterable<Person> findByNameLike(String name);

}
