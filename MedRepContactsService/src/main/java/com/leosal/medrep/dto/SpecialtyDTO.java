package com.leosal.medrep.dto;

import com.leosal.medrep.entity.Specialty;

public class SpecialtyDTO {
	private Long id;
	private String name;
	
	public SpecialtyDTO() {
		super();
	}
	
	public SpecialtyDTO(Specialty specialty) {
		super();
		
		if(specialty != null) {
			this.id = specialty.getId();
			this.name = specialty.getName();
		}
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
}
