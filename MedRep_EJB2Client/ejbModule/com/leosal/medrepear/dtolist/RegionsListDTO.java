package com.leosal.medrepear.dtolist;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.leosal.medrepear.dto.RegionDTO;

@XmlRootElement(name="regions")
@XmlAccessorType(XmlAccessType.FIELD)
public class RegionsListDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name="region")
	private List<RegionDTO> list;
	
	public RegionsListDTO(){}
	public RegionsListDTO(List<RegionDTO> l){
		this.list=l;
	}

	public List<RegionDTO> getList() {
		return list;
	}

	public void setList(List<RegionDTO> list) {
		this.list = list;
	}

}
