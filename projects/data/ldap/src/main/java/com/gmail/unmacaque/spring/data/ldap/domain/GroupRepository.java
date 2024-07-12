package com.gmail.unmacaque.spring.data.ldap.domain;

import org.springframework.data.ldap.repository.LdapRepository;

public interface GroupRepository extends LdapRepository<Group> {

	Iterable<Group> findByMember(String memberDn);

}
