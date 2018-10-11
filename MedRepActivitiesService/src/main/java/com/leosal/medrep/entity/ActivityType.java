package com.leosal.medrep.entity;

import java.io.Serializable;

import com.leosal.dbutils.DBEntity;

public class ActivityType implements DBEntity, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private boolean groupActivity;
	
	public ActivityType() {
		super();
	}

	public ActivityType(Long id, String name, boolean groupActivity) {
		super();
		this.id = id;
		this.name = name;
		this.groupActivity = groupActivity;
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

	public boolean isGroupActivity() {
		return groupActivity;
	}

	public void setGroupActivity(boolean groupActivity) {
		this.groupActivity = groupActivity;
	}


}
