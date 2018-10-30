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
 * The persistent class for the distrib_prod database table.
 * 
 */
@Entity
@Table(name="distrib_prod")
@NamedQuery(name="DistribProd.findAll", query="SELECT d FROM DistribProd d")
public class DistribProd implements DBEntity, Serializable,
	Comparable<DistribProd>{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="distrib_cod")
	private String distribCod;

	@Column(name="prod_name")
	private String prodName;

	//bi-directional many-to-one association to Institution
	@ManyToOne
	@JoinColumn(name="distrib_id")
	private Institution institution;

	//bi-directional many-to-one association to Product
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="prod_id")
	private Product product;

	public DistribProd() {
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

	public String getProdName() {
		return this.prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public Institution getInstitution() {
		return this.institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	@Override
	public int compareTo(DistribProd o) {
		
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
        DistribProd e = (DistribProd) o;
        return (this.getId().equals(e.getId()));
	}

}