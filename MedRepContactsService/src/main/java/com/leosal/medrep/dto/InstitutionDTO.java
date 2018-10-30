package com.leosal.medrep.dto;

import com.leosal.medrep.entity.Institution;

public class InstitutionDTO {
	private Integer id;
	private byte active;
	private String code;
	private boolean distributor;
	private String name;
	private InstitTypeDTO institType;
	private RegionDTO region;
	
	public InstitutionDTO() {
		
	}

	public InstitutionDTO(Institution institution) {
		super();
		
		if(institution != null) {
			this.id = institution.getId();
			this.active = institution.getActive();
			this.code = institution.getCode();
			this.distributor = institution.getDistributor() > 0;
			this.name = institution.getName();
			this.institType = new InstitTypeDTO(institution.getInstitType());
			this.region = new RegionDTO(institution.getRegion());
		}
		
	}
	
	public InstitutionDTO(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte getActive() {
		return active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isDistributor() {
		return distributor;
	}

	public void setDistributor(boolean distributor) {
		this.distributor = distributor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public InstitTypeDTO getInstitType() {
		return institType;
	}

	public void setInstitType(InstitTypeDTO institType) {
		this.institType = institType;
	}

	public RegionDTO getRegion() {
		return region;
	}

	public void setRegion(RegionDTO region) {
		this.region = region;
	}
	
	
}
