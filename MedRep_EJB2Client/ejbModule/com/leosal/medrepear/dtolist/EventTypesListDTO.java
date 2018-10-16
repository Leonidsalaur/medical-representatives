package com.leosal.medrepear.dtolist;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.leosal.medrepear.dto.EventTypeDTO;

@XmlRootElement(name="event_types")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventTypesListDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name="event_type")
	private List<EventTypeDTO> list;
	
	public EventTypesListDTO(){}
	public EventTypesListDTO(List<EventTypeDTO> list){
		this.list=list;
	}
	public List<EventTypeDTO> getList() {
		return list;
	}
	public void setList(List<EventTypeDTO> list) {
		this.list = list;
	}
	
	public void addEventDTO(EventTypeDTO ev){
		list.add(ev);
	}
	public void removeEventDTO(EventTypeDTO ev){
		list.remove(ev);
	}

}
