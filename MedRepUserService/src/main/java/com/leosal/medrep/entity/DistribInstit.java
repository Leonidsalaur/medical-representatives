package com.leosal.medrep.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.leosal.dbutils.DBEntity;


/**
 * The persistent class for the distrib_instit database table.
 * 
 */
@Entity
@Table(name="distrib_instit")
@NamedQuery(name="DistribInstit.findAll", query="SELECT d FROM DistribInstit d")
public class DistribInstit implements DBEntity, Serializable,
	Comparable<DistribInstit>{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="distrib_cod")
	private String distribCod;

	@Column(name="instit_name")
	private String institName;

	//bi-directional many-to-one association to Institution
	@ManyToOne
	@JoinColumn(name="distrib_id")
	private Institution institution1;

	//bi-directional many-to-one association to Institution
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="instit_id")
	private Institution institution2;

	public DistribInstit() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDistribCod() {
		return this.distribCod;
	}

	public void setDistribCod(String distribCod) {
		this.distribCod = distribCod;
	}

	public String getInstitName() {
		return this.institName;
	}

	public void setInstitName(String institName) {
		this.institName = institName;
	}

	public Institution getInstitution1() {
		return this.institution1;
	}

	public void setInstitution1(Institution institution1) {
		this.institution1 = institution1;
	}

	public Institution getInstitution2() {
		return this.institution2;
	}

	public void setInstitution2(Institution institution2) {
		this.institution2 = institution2;
	}
	@Override
	public int compareTo(DistribInstit o) {
		
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
        DistribInstit e = (DistribInstit) o;
        return (this.getId().equals(e.getId()));
	}

}