package com.leosal.medrep.entity;

import java.io.Serializable;

import com.leosal.dbutils.DBEntity;

public class Advert implements Serializable, DBEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private Long price;
	
	public Advert() {
		
	}
	
	public Advert(Long id, String name, Long price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}
	
	

}
