package com.leosal.medrepear.dtolist;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.leosal.medrepear.dto.InstitutionDTO;

@XmlRootElement(name="institutions")
@XmlAccessorType(XmlAccessType.FIELD)
public class InstitutionsListDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlElement(name="institution")
	private List<InstitutionDTO> list;
	
	public InstitutionsListDTO(){}
	public InstitutionsListDTO(List<InstitutionDTO> list){
		this.list=list;
	}
	public List<InstitutionDTO> getList() {
		return list;
	}
	public void setList(List<InstitutionDTO> list) {
		this.list = list;
	}
	

}
