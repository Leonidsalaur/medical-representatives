package com.leosal.medrepear.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the regions database table.
 * 
 */
@Entity
@Table(name="regions")
@NamedQuery(name="Region.findAll", query="SELECT r FROM Region r")
public class Region extends com.leosal.util.jpa.Entity implements Serializable,
		Comparable<Region>{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String name;

	//bi-directional many-to-one association to Institution
	@OneToMany(mappedBy="region")
	private Set<Institution> institutions;

	//bi-directional many-to-one association to PersonsRegion
	@OneToMany(mappedBy="region")
	private Set<PersonsRegion> personsRegions;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="rep_id")
	private Person person;

	public Region() {
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

	public Set<Institution> getInstitutions() {
		return this.institutions;
	}

	public void setInstitutions(Set<Institution> institutions) {
		this.institutions = institutions;
	}

	public Institution addInstitution(Institution institution) {
		getInstitutions().add(institution);
		institution.setRegion(this);

		return institution;
	}

	public Institution removeInstitution(Institution institution) {
		getInstitutions().remove(institution);
		institution.setRegion(null);

		return institution;
	}

	public Set<PersonsRegion> getPersonsRegions() {
		return this.personsRegions;
	}

	public void setPersonsRegions(Set<PersonsRegion> personsRegions) {
		this.personsRegions = personsRegions;
	}

	public PersonsRegion addPersonsRegion(PersonsRegion personsRegion) {
		getPersonsRegions().add(personsRegion);
		personsRegion.setRegion(this);

		return personsRegion;
	}

	public PersonsRegion removePersonsRegion(PersonsRegion personsRegion) {
		getPersonsRegions().remove(personsRegion);
		personsRegion.setRegion(null);

		return personsRegion;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	@Override
	public int compareTo(Region o) {
		
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