package com.leosal.medrep.entity;

import com.leosal.dbutils.DBEntity;

public class Institution implements DBEntity {
	
	private Long id;
	private String institution;
	private Region region;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}

}
