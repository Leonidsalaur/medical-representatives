package com.leosal.medrepear.dto;

import java.io.Serializable;



public class UniversityDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String type;



	public UniversityDTO() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}
