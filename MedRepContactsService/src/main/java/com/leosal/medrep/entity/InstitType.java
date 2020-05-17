package com.leosal.medrep.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.leosal.dbutils.DBEntity;

import java.util.Set;


/**
 * The persistent class for the instit_types database table.
 * 
 */
@Entity
@Table(name="instit_types")
@NamedQuery(name="InstitType.findAll", query="SELECT i FROM InstitType i")
public class InstitType implements DBEntity, Serializable,
		Comparable<InstitType>{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToMany(mappedBy="institType")
	private Set<Institution> institutions;

	public InstitType() {
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

	public Set<Institution> getInstitutions() {
		return this.institutions;
	}

	public void setInstitutions(Set<Institution> institutions) {
		this.institutions = institutions;
	}

	public Institution addInstitution(Institution institution) {
		getInstitutions().add(institution);
		institution.setInstitType(this);

		return institution;
	}

	public Institution removeInstitution(Institution institution) {
		getInstitutions().remove(institution);
		institution.setInstitType(null);

		return institution;
	}
	
	@Override
	public int compareTo(InstitType o) {
		
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