package com.leosal.medrep.dto;

import com.leosal.medrep.entity.EducType;

public class EducTypeDTO {
	private Integer id;
	private String type;

	public EducTypeDTO() {
		super();
	}
	
	public EducTypeDTO(EducType et) {
		super();
		
		if(et != null) {
			this.id = et.getId();
			this.type = et.getType();
		}
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
