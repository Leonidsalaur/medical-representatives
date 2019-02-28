package com.leosal.medrep.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.leosal.medrep.entity.MedrepUser;

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
	private Set<String> roles;
	

	public MedRepUserDTO() {
		super();
	}
	
	public MedRepUserDTO(MedrepUser user) {
		super();
		this.id = user.getId().intValue();
		this.login = user.getLogin();
		this.password = user.getPassword();
		this.firstName = user.getFirstname();
		this.midName = user.getMidname();
		this.lastName = user.getLastname();
		this.birthday = user.getBirthday();
		
		roles = new HashSet<>();
		user.getRoles().forEach(role -> roles.add(role.getRole().toString()));
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

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	
	
	
}
