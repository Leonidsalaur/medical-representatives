package com.leosal.medrepear.dtolist;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.leosal.medrepear.dto.InstitTypeDTO;

@XmlRootElement(name="institution_types")
@XmlAccessorType(XmlAccessType.FIELD)
public class InstitutionTypesListDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name="institution_type")
	private List<InstitTypeDTO> list;
	
	public InstitutionTypesListDTO(){
		
	}
	public InstitutionTypesListDTO(List<InstitTypeDTO> list){
		this.list=list;
	}
	public List<InstitTypeDTO> getList() {
		return list;
	}
	public void setList(List<InstitTypeDTO> list) {
		this.list = list;
	}
	

}
