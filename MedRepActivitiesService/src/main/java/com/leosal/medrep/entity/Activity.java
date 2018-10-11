package com.leosal.medrep.entity;

import java.io.Serializable;
import java.util.Date;

import com.leosal.dbutils.DBEntity;

public class Activity implements DBEntity, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Date date;
	private ActivityType type;
	private String comment;
	private MedrepUser owner;
	
	
	
	
	public Activity() {
		super();
	}
	
	public Activity(Long id, Date date, ActivityType type, String comment) {
		super();
		this.id = id;
		this.date = date;
		this.type = type;
		this.comment = comment;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public ActivityType getType() {
		return type;
	}
	public void setType(ActivityType type) {
		this.type = type;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

}
