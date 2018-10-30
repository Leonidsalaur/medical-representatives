package com.leosal.medrep.dto;

import com.leosal.medrep.entity.Region;
import com.leosal.medrep.entity.Specialty;

public class RegionDTO {
	private Integer id;
	private String name;
	
	public RegionDTO() {
		super();
	}
	
	public RegionDTO(Region region) {
		super();
		
		if(region != null) {
			this.id = region.getId();
			this.name = region.getName();
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
