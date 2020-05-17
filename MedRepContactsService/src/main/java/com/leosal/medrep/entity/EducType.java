package com.leosal.medrep.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.leosal.dbutils.DBEntity;

import java.util.Set;


/**
 * The persistent class for the educ_types database table.
 * 
 */
@Entity
@Table(name="educ_types")
@NamedQuery(name="EducType.findAll", query="SELECT e FROM EducType e")
public class EducType implements DBEntity, Serializable,
	Comparable<EducType>{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String type;

	@OneToMany(mappedBy="educType")
	private Set<Contact> persons;

	public EducType() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<Contact> getPersons() {
		return this.persons;
	}

	public void setPersons(Set<Contact> persons) {
		this.persons = persons;
	}

	public Contact addPerson(Contact person) {
		getPersons().add(person);
		person.setEducType(this);

		return person;
	}

	public Contact removePerson(Contact person) {
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