package com.leosal.medrepear.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the med_category database table.
 * 
 */
@Entity
@Table(name="med_category")
@NamedQuery(name="MedCategory.findAll", query="SELECT m FROM MedCategory m")
public class MedCategory extends com.leosal.util.jpa.Entity implements Serializable,
		Comparable<MedCategory>{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private int frequency;

	private String name;

	public MedCategory() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getFrequency() {
		return this.frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int compareTo(MedCategory o) {
		
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