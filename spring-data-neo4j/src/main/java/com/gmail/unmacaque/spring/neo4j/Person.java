package com.gmail.unmacaque.spring.neo4j;

import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Person {

	@GraphId
	private Long id;

	private String name;
	private int age;
	private String gender;

	@Relationship(type = "PURCHASES", direction = Relationship.UNDIRECTED)
	private Set<Product> purchases;

	@Relationship(type = "RECOMMENDATIONS", direction = Relationship.UNDIRECTED)
	private Set<Product> recommendations;

	@Relationship(type = "ACQUAINTANCE", direction = Relationship.UNDIRECTED)
	private Set<Person> acquaintances;

	/**
	 * Neo4j requires a no-args constructor
	 */
	public Person() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Set<Product> getPurchases() {
		return purchases;
	}

	public void setPurchases(Set<Product> purchases) {
		this.purchases = purchases;
	}

	public Set<Product> getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(Set<Product> recommendations) {
		this.recommendations = recommendations;
	}

	public Set<Person> getAcquaintances() {
		return acquaintances;
	}

	public void setAcquaintances(Set<Person> acquaintances) {
		this.acquaintances = acquaintances;
	}
}
