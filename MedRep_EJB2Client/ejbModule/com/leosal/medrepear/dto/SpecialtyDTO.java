package com.leosal.medrepear.dto;

import java.io.Serializable;


public class SpecialtyDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Integer id;

	private String name;



	public SpecialtyDTO() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString(){
		return getName();
	}

	
}
