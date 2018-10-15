package com.leosal.medrep.entity;

import com.leosal.dbutils.DBEntity;

public class University implements DBEntity {
	private Long id;
	private String university;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
}
