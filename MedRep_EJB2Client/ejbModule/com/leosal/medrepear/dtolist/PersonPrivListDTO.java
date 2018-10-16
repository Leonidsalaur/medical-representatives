package com.leosal.medrepear.dtolist;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.leosal.medrepear.dto.PersonPrivDTO;

@XmlRootElement(name="person_privilegies")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonPrivListDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name="privilegy")
	private List<PersonPrivDTO> list;
	
	public PersonPrivListDTO(){
		
	}
	public PersonPrivListDTO(List<PersonPrivDTO> list){
		this.list=list;
	}
	public List<PersonPrivDTO> getList() {
		return list;
	}
	public void setList(List<PersonPrivDTO> list) {
		this.list = list;
	}
	

}
