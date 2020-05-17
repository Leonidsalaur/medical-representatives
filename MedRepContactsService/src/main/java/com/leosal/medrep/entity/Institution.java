package com.leosal.medrep.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.leosal.dbutils.DBEntity;


/**
 * The persistent class for the institutions database table.
 * 
 */
@Entity
@Table(name="institutions")
@NamedQuery(name="Institution.findAll", query="SELECT i FROM Institution i")
public class Institution implements DBEntity, Serializable,
		Comparable<Institution>{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private byte active;

	private String code;

	private byte distributor;

	private String name;

	@ManyToMany(mappedBy="institutions")
	private Set<Contact> persons;

	@ManyToOne
	@JoinColumn(name="inst_type_id")
	private InstitType institType;

	@ManyToOne
	@JoinColumn(name="region_id")
	private Region region;

	public Institution() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public byte getDistributor() {
		return this.distributor;
	}

	public void setDistributor(byte distributor) {
		this.distributor = distributor;
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

	public InstitType getInstitType() {
		return this.institType;
	}

	public void setInstitType(InstitType institType) {
		this.institType = institType;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	
	@Override
	public int compareTo(Institution o) {
		
		return this.getName().compareToIgnoreCase(o.getName());
	}
	
	@Override
	 public int hashCode()
	 {
		//System.out.println("Institution name: "+getName()+"\nInstitution id: "+getId());
	    final int PRIME = 31;
	    int result = 1;
	    result = (int) (PRIME * result + (getId()==null?
	    		getName().hashCode()
	    		:getName().hashCode()+getId()));
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