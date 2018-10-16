package com.leosal.medrepear.dto;

import java.io.Serializable;
import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="advert")
public class AdvertDTO implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;


	private Integer id;
	private BigInteger barCode;
	private String name;
	private float price;
	
	public AdvertDTO() {
	}

	@XmlAttribute
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@XmlAttribute
	public BigInteger getBarCode() {
		return this.barCode;
	}

	public void setBarCode(BigInteger barCode) {
		this.barCode = barCode;
	}

	@XmlAttribute
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	@Override
	public AdvertDTO clone(){
		AdvertDTO advert = new AdvertDTO();
		advert.setBarCode(this.getBarCode());
		advert.setId(this.getId());
		advert.setName(this.getName());
		advert.setPrice(this.getPrice());
		return advert;
	}
	
	public void getFrom(AdvertDTO advert){
		
		this.setBarCode(advert.getBarCode());
		this.setId(advert.getId());
		this.setName(advert.getName());
		this.setPrice(advert.getPrice());
	}
	
	public String toString(){
		return name;
	}

	
}
