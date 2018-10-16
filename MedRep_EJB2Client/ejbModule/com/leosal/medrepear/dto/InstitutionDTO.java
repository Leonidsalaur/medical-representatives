package com.leosal.medrepear.dto;

import java.io.Serializable;
import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.internal.txw2.annotation.XmlElement;

@XmlRootElement(name="institution")
public class InstitutionDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private boolean active;
	private BigInteger code;
	private boolean distributor;
	private String name;
	private InstitTypeDTO institType;
	private RegionDTO region;


	public InstitutionDTO() {
		this.code=new BigInteger("0");
	}

	@XmlAttribute
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@XmlElement
	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@XmlElement
	public BigInteger getCode() {
		return this.code;
	}

	public void setCode(BigInteger code) {
		this.code = code;
	}

	@XmlElement
	public boolean isDistributor() {
		return this.distributor;
	}

	public void setDistributor(boolean distributor) {
		this.distributor = distributor;
	}

	@XmlElement
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public InstitTypeDTO getInstitType() {
		return institType;
	}

	public void setInstitType(InstitTypeDTO institType) {
		this.institType = institType;
	}

	@XmlElement
	public RegionDTO getRegion() {
		return region;
	}

	public void setRegion(RegionDTO region) {
		this.region = region;
	}
	
	@Override
	public String toString(){
		return getName();
	}
	
	@Override
	public boolean equals(Object o) {
        if(o == null)
        {
            return false;
        }
        if (o == this)
        {
           return true;
        }
        if (getClass() != o.getClass())
        {
            return false;
        }
        InstitutionDTO e = (InstitutionDTO) o;
        return (this.getId().equals(e.getId()));
	}

	

}
