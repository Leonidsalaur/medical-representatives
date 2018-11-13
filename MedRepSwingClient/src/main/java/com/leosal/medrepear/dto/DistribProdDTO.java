package com.leosal.medrepear.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="distrib_product")
@XmlAccessorType(XmlAccessType.FIELD)
public class DistribProdDTO implements Serializable,
	Comparable<DistribProdDTO>{
	private static final long serialVersionUID = 1L;

	@XmlAttribute(name="id")
	private Integer id;
	
	@XmlAttribute(name="distrib_cod")
	private String distribCod;
	
	@XmlAttribute(name="product_name")
	private String prodName;
	
	@XmlElement(name="distributor")
	private InstitutionDTO distributor;
	
	@XmlElement(name="product")
	private ProductDTO product;

	public DistribProdDTO() {
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

	
	
	

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public InstitutionDTO getDistributor() {
		return distributor;
	}

	public void setDistributor(InstitutionDTO distributor) {
		this.distributor = distributor;
	}

	@Override
	public int compareTo(DistribProdDTO o) {
		
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
        DistribProdDTO e = (DistribProdDTO) o;
        return (this.getId().equals(e.getId()));
	}

}