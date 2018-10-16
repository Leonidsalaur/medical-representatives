package com.leosal.medrepear.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the person_types database table.
 * 
 */
@Entity
@Table(name="person_types")
@NamedQuery(name="PersonType.findAll", query="SELECT p FROM PersonType p")
public class PersonType extends com.leosal.util.jpa.Entity implements Serializable,
		Comparable<PersonType>{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String name;

	//bi-directional many-to-one association to Person
	@OneToMany(mappedBy="personType")
	private Set<Person> persons;

	public PersonType() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

	public Person addPerson(Person person) {
		getPersons().add(person);
		person.setPersonType(this);

		return person;
	}

	public Person removePerson(Person person) {
		getPersons().remove(person);
		person.setPersonType(null);

		return person;
	}

	@Override
	public int compareTo(PersonType o) {
		
		return this.getId().compareTo(o.getId());
	}
	
	@Override
	 public int hashCode()
	 {
	    final int PRIME = 31;
	    int result = 1;
	    result = PRIME * result + getId();
	    return result;
	 }
	
	@Override
	public boolean equals(Object o) {
        if(o == null)
        {
            return false;
        }
        if (o == this)
        {
           return true;
        }
        if (getClass() != o.getClass())
        {
            return false;
        }
        com.leosal.util.jpa.Entity e = (com.leosal.util.jpa.Entity) o;
        return (this.getId().equals(e.getId()));
	}
}