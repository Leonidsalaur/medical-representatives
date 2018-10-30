package com.leosal.medrep.dto;

import com.leosal.medrep.entity.University;

public class UniversityDTO {
	private Integer id;
	private String name;
	
	public UniversityDTO() {
		super();
	}
	
	public UniversityDTO(University university) {
		super();
		
		if(university != null) {
			this.id = university.getId();
			this.name = university.getType();
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
