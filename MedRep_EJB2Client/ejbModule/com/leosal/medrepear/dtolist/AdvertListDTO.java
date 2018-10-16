package com.leosal.medrepear.dtolist;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.leosal.medrepear.dto.AdvertDTO;

@XmlRootElement(name="adverts")
@XmlAccessorType(XmlAccessType.FIELD)
public class AdvertListDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name="advert")
	private List<AdvertDTO> list;
	
	public AdvertListDTO(){
		
	}
	public AdvertListDTO(List<AdvertDTO> list){
		this.list=list;
	}
	public List<AdvertDTO> getList() {
		return list;
	}
	public void setList(List<AdvertDTO> list) {
		this.list = list;
	}
	

}
