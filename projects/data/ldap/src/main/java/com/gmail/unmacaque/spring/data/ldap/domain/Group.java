package com.gmail.unmacaque.spring.data.ldap.domain;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;
import java.util.List;

@Entry(base = "ou=groups,dc=springframework,dc=org", objectClasses = {"groupOfUniqueNames"})
public final class Group {

	@Id
	private Name dn;

	@Attribute(name = "cn")
	private String name;

	@Attribute(name = "uniquemember")
	private List<String> member;

	public String getName() {
		return name;
	}

	public List<String> getMember() {
		return member;
	}
}
