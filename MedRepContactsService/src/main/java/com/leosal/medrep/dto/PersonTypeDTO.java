package com.leosal.medrep.dto;

import com.leosal.medrep.entity.PersonType;
import com.leosal.medrep.entity.Specialty;

public class PersonTypeDTO {
	private Integer id;
	private String name;
	
	public PersonTypeDTO() {
		super();
	}
	
	public PersonTypeDTO(PersonType personType) {
		super();
		
		if(personType != null) {
			this.id = personType.getId();
			this.name = personType.getName();
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
