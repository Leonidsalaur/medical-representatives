package com.leosal.medrepear.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the persons_regions database table.
 * 
 */
@Entity
@Table(name="persons_regions")
@NamedQuery(name="PersonsRegion.findAll", query="SELECT p FROM PersonsRegion p")
public class PersonsRegion extends com.leosal.util.jpa.Entity implements Serializable,
		Comparable<PersonsRegion>{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	//bi-directional many-to-one association to Person
	@ManyToOne
	private Person person;

	//bi-directional many-to-one association to Region
	@ManyToOne
	private Region region;

	public PersonsRegion() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}
	
	@Override
	public int compareTo(PersonsRegion o) {
		
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