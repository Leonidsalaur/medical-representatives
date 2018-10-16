package com.leosal.medrepear.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the educ_types database table.
 * 
 */
@Entity
@Table(name="educ_types")
@NamedQuery(name="EducType.findAll", query="SELECT e FROM EducType e")
public class EducType extends com.leosal.util.jpa.Entity implements Serializable,
	Comparable<EducType>{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String type;

	//bi-directional many-to-one association to Person
	@OneToMany(mappedBy="educType")
	private Set<Person> persons;

	public EducType() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

	public Person addPerson(Person person) {
		getPersons().add(person);
		person.setEducType(this);

		return person;
	}

	public Person removePerson(Person person) {
		getPersons().remove(person);
		person.setEducType(null);

		return person;
	}
	
	@Override
	public int compareTo(EducType o) {
		
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