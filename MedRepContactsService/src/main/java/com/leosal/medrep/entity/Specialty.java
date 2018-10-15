package com.leosal.medrep.entity;

import com.leosal.dbutils.DBEntity;

public class Specialty implements DBEntity {

	private Long id;
	private String specialty;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

}
