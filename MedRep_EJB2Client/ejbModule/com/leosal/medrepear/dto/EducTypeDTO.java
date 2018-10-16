package com.leosal.medrepear.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="education_type")
public class EducTypeDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Integer id;

	private String type;


	public EducTypeDTO() {
	}
	
	@XmlAttribute
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@XmlAttribute
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}
