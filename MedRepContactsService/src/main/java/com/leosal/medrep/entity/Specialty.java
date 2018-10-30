package com.leosal.medrep.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.leosal.dbutils.DBEntity;

import java.util.Set;


/**
 * The persistent class for the specialties database table.
 * 
 */
@Entity
@Table(name="specialties")
@NamedQuery(name="Specialty.findAll", query="SELECT s FROM Specialty s")
public class Specialty implements DBEntity, Serializable,
		Comparable<Specialty>{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String name;

	@OneToMany(mappedBy="specialty1")
	private Set<Contact> persons1;

	@OneToMany(mappedBy="specialty2")
	private Set<Contact> persons2;

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

	public Set<Contact> getPersons1() {
		return this.persons1;
	}

	public void setPersons1(Set<Contact> persons1) {
		this.persons1 = persons1;
	}

	public Contact addPersons1(Contact persons1) {
		getPersons1().add(persons1);
		persons1.setSpecialty1(this);

		return persons1;
	}

	public Contact removePersons1(Contact persons1) {
		getPersons1().remove(persons1);
		persons1.setSpecialty1(null);

		return persons1;
	}

	public Set<Contact> getPersons2() {
		return this.persons2;
	}

	public void setPersons2(Set<Contact> persons2) {
		this.persons2 = persons2;
	}

	public Contact addPersons2(Contact persons2) {
		getPersons2().add(persons2);
		persons2.setSpecialty2(this);

		return persons2;
	}

	public Contact removePersons2(Contact persons2) {
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