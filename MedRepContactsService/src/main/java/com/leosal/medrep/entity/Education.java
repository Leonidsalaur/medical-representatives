package com.leosal.medrep.entity;

import com.leosal.dbutils.DBEntity;

public class Education implements DBEntity {
	private Long id;
	private String education;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
}
