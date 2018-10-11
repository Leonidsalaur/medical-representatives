package com.leosal.medrep.entity;

import java.io.Serializable;
import java.util.Date;

import com.leosal.dbutils.DBEntity;

public class Contact implements Serializable, DBEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String firstName;
	private String midName;
	private String lastName;
	private Date birthDay;

	public Contact() {
		super();
	}
	
	public Contact(Long id, String firstName, String midName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.midName = midName;
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMidName() {
		return midName;
	}

	public void setMidName(String midName) {
		this.midName = midName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

}
