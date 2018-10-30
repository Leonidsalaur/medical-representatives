package com.leosal.medrep.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.leosal.dbutils.DBEntity;


/**
 * The persistent class for the sales database table.
 * 
 */
@Entity
@Table(name="sales")
@NamedQuery(name="Sale.findAll", query="SELECT s FROM Sale s")
public class Sale implements DBEntity, Serializable,
		Comparable<Sale>{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private float quant;

	//bi-directional many-to-one association to Institution
	@ManyToOne
	@JoinColumn(name="inst_id")
	private Institution institution;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="prod_id")
	private Product product;

	//bi-directional many-to-one association to Report
	@ManyToOne
	private Report report;

	public Sale() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public float getQuant() {
		return this.quant;
	}

	public void setQuant(float quant) {
		this.quant = quant;
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

	public Report getReport() {
		return this.report;
	}

	public void setReport(Report report) {
		this.report = report;
	}
	
	@Override
	public int compareTo(Sale o) {
		
		return this.getId().compareTo(o.getId());
	}
	
	@Override
	 public int hashCode()
	 {
	    final int PRIME = 31;
	    int result = 1;
	    result = (int) (PRIME * result + (getId()!=null?getId():0)+getInstitution().hashCode()+getProduct().hashCode());
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