package com.leosal.medrepear.dtolist;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.leosal.medrepear.dto.PersonTypeDTO;

@XmlRootElement(name="person_types")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonTypeListDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name="person_type")
	private List<PersonTypeDTO> list;
	
	public PersonTypeListDTO(){
		
	}
	public PersonTypeListDTO(List<PersonTypeDTO> list){
		this.list=list;
	}
	public List<PersonTypeDTO> getList() {
		return list;
	}
	public void setList(List<PersonTypeDTO> list) {
		this.list = list;
	}
	

}
