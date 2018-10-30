package com.leosal.medrep.dto;

import java.io.Serializable;
import java.util.Date;

import com.leosal.medrep.entity.MedrepUser;
import com.leosal.medrep.entity.Person;

public class MedRepUserDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String login;
	private String password;
	private String firstName;
	private String midName;
	private String lastName;
	private Date birthday;
	

	public MedRepUserDTO() {
		super();
	}
	
	public MedRepUserDTO(Person user) {
		super();
		this.id = user.getId();
		this.login = user.getLogin();
		this.password = user.getPassword();
		this.firstName = user.getFirstname();
		this.midName = user.getMidname();
		this.lastName = user.getLastname();
		this.birthday = user.getBirthday();
	}
	
	public MedRepUserDTO(MedrepUser user) {
		super();
		this.id = user.getId();
		this.login = user.getLogin();
		this.password = user.getPassword();
		this.firstName = user.getFirstname();
		this.midName = user.getMidname();
		this.lastName = user.getLastname();
		this.birthday = user.getBirthday();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	
	
}
