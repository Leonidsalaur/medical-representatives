package com.leosal.medrep.dto;

import com.leosal.medrep.entity.University;

public class UniversityDTO {
	private Long id;
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
