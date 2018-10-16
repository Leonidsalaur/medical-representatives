package com.leosal.medrepear.dtolist;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.leosal.medrepear.dto.PersonDTO;

@XmlRootElement(name="persons")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonsListDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name="person")
	private List<PersonDTO> list;
	
	public PersonsListDTO(){}
	public PersonsListDTO(List<PersonDTO> list){
		this.list=list;
	}
	public List<PersonDTO> getList() {
		return  list;
	}
	public void setList(List<PersonDTO> list) {
		this.list = list;
	}

}
