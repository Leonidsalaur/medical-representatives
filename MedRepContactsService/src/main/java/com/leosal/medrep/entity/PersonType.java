package com.leosal.medrep.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.leosal.dbutils.DBEntity;

import java.util.Set;


/**
 * The persistent class for the person_types database table.
 * 
 */
@Entity
@Table(name="person_types")
@NamedQuery(name="PersonType.findAll", query="SELECT p FROM PersonType p")
public class PersonType implements DBEntity, Serializable,
		Comparable<PersonType>{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToMany(mappedBy="personType")
	private Set<Contact> persons;

	public PersonType() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Contact> getPersons() {
		return this.persons;
	}

	public void setPersons(Set<Contact> persons) {
		this.persons = persons;
	}

	public Contact addPerson(Contact person) {
		getPersons().add(person);
		person.setPersonType(this);

		return person;
	}

	public Contact removePerson(Contact person) {
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
	    result = PRIME * result + getId().intValue();
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
        DBEntity e = (DBEntity) o;
        return (this.getId().equals(e.getId()));
	}
}