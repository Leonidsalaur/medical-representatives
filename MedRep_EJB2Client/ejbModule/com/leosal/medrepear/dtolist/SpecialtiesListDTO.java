package com.leosal.medrepear.dtolist;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.leosal.medrepear.dto.SpecialtyDTO;

@XmlRootElement(name="specialties")
@XmlAccessorType(XmlAccessType.FIELD)
public class SpecialtiesListDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name="specialty")
	private List<SpecialtyDTO> list;
	
	public SpecialtiesListDTO(){}
	public SpecialtiesListDTO(List<SpecialtyDTO> list){
		this.list=list;
	}
	public List<SpecialtyDTO> getList() {
		return list;
	}
	public void setList(List<SpecialtyDTO> list) {
		this.list = list;
	}

}
