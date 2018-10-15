package com.leosal.medrep.entity;

import com.leosal.dbutils.DBEntity;

public class Region implements DBEntity {
	
	private Long id;
	private String region;
	private MedrepUser user;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public MedrepUser getUser() {
		return user;
	}
	public void setUser(MedrepUser user) {
		this.user = user;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}

}
