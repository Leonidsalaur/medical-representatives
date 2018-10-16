package com.leosal.medrepear.dto;

import java.io.Serializable;
import java.math.BigInteger;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="product")
public class ProductDTO implements Serializable, Comparable<ProductDTO> {
	private static final long serialVersionUID = 1L;


	private Integer id;
	private BigInteger barCode;

	private String name;

	private float price;

	

	public ProductDTO() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigInteger getBarCode() {
		return this.barCode;
	}

	public void setBarCode(BigInteger barCode) {
		this.barCode = barCode;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public int compareTo(ProductDTO another) {
		return this.toString().compareToIgnoreCase(another.toString());
	}
	
	@Override
	public String toString(){
		return this.getName()+(this.getBarCode().longValue()>0?" ("+this.getBarCode().longValue()+")":"");
	}

}
