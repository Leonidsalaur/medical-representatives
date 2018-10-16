package com.leosal.medrepear.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the specialties database table.
 * 
 */
@Entity
@Table(name="specialties")
@NamedQuery(name="Specialty.findAll", query="SELECT s FROM Specialty s")
public class Specialty extends com.leosal.util.jpa.Entity implements Serializable,
		Comparable<Specialty>{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String name;

	//bi-directional many-to-one association to Person
	@OneToMany(mappedBy="specialty1")
	private Set<Person> persons1;

	//bi-directional many-to-one association to Person
	@OneToMany(mappedBy="specialty2")
	private Set<Person> persons2;

	public Specialty() {
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

	public Set<Person> getPersons1() {
		return this.persons1;
	}

	public void setPersons1(Set<Person> persons1) {
		this.persons1 = persons1;
	}

	public Person addPersons1(Person persons1) {
		getPersons1().add(persons1);
		persons1.setSpecialty1(this);

		return persons1;
	}

	public Person removePersons1(Person persons1) {
		getPersons1().remove(persons1);
		persons1.setSpecialty1(null);

		return persons1;
	}

	public Set<Person> getPersons2() {
		return this.persons2;
	}

	public void setPersons2(Set<Person> persons2) {
		this.persons2 = persons2;
	}

	public Person addPersons2(Person persons2) {
		getPersons2().add(persons2);
		persons2.setSpecialty2(this);

		return persons2;
	}

	public Person removePersons2(Person persons2) {
		getPersons2().remove(persons2);
		persons2.setSpecialty2(null);

		return persons2;
	}
	
	@Override
	public int compareTo(Specialty o) {
		
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