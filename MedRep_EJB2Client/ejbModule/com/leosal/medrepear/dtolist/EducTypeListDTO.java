package com.leosal.medrepear.dtolist;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.leosal.medrepear.dto.EducTypeDTO;

@XmlRootElement(name="educ_types")
@XmlAccessorType(XmlAccessType.FIELD)
public class EducTypeListDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name="educ_type")
	private List<EducTypeDTO> list;
	
	public EducTypeListDTO(){}
	public EducTypeListDTO(List<EducTypeDTO> list){
		this.list=list;
	}
	public List<EducTypeDTO> getList() {
		return list;
	}
	public void setList(List<EducTypeDTO> list) {
		this.list = list;
	}
	

}
