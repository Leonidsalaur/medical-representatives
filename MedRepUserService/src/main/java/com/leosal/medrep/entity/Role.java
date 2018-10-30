package com.leosal.medrep.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.leosal.dbutils.DBEntity;
import com.leosal.medrep.enums.RoleType;

@Entity
@Table(name="roles")
public class Role implements DBEntity{
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Enumerated(EnumType.STRING)
	@Column
	private RoleType role;
	@OneToMany(mappedBy="role")
	private Set<UserToRole> roles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}

	public Set<UserToRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserToRole> roles) {
		this.roles = roles;
	}

}
