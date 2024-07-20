package com.gmail.unmacaque.spring.data.ldap.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

@Entry(base = "ou=people,dc=springframework,dc=org", objectClasses = {"inetOrgPerson"})
public final class Person {

	@Id
	@JsonIgnore
	private Name dn;

	@DnAttribute(value = "uid")
	private String uid;

	@Attribute(name = "cn")
	private String name;

	public String getDn() {
		return dn.toString();
	}

	public String getUid() {
		return uid;
	}

	public String getName() {
		return name;
	}

}
