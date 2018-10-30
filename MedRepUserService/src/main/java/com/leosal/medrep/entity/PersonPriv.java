package com.leosal.medrep.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.leosal.dbutils.DBEntity;


/**
 * The persistent class for the person_privs database table.
 * 
 */
@Entity
@Table(name="person_privs")
@NamedQuery(name="PersonPriv.findAll", query="SELECT p FROM PersonPriv p")
public class PersonPriv implements DBEntity, Serializable,
	Comparable<PersonPriv>{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="priv_id")
	private int privId;

	//bi-directional many-to-one association to Person
	@ManyToOne
	private Person person;

	public PersonPriv() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getPrivId() {
		return this.privId;
	}

	public void setPrivId(int privId) {
		this.privId = privId;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	@Override
	public int compareTo(PersonPriv o) {
		
		return this.getId().compareTo(o.getId());
	}
	
	@Override
	 public int hashCode()
	 {
	    final int PRIME = 31;
	    int result = 1;
	    result = (int) (PRIME * result + getPrivId()+(getPerson()!=null?(getPerson().getId()!=null?getPerson().getId():0):0));
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
        PersonPriv e = (PersonPriv) o;
        return (this.getId().equals(e.getId()));
	}

}