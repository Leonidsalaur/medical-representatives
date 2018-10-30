package com.leosal.medrep.dto;

import com.leosal.medrep.entity.InstitType;
import com.leosal.medrep.entity.Specialty;

public class InstitTypeDTO {
	private Integer id;
	private String name;
	
	public InstitTypeDTO() {
		super();
	}
	
	public InstitTypeDTO(InstitType type) {
		super();
		
		if(type != null) {
			this.id = type.getId();
			this.name = type.getName();
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
