package com.leosal.medrepear.dtolist;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.leosal.medrepear.dto.EventDTO;

@XmlRootElement(name="events")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventsListDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name="event")
	private List<EventDTO> list;
	
	public EventsListDTO(){}
	public EventsListDTO(List<EventDTO> list){
		this.list=list;
	}
	public List<EventDTO> getList() {
		return list;
	}
	public void setList(List<EventDTO> list) {
		this.list = list;
	}
	/*
	public void addEventDTO(EventDTO ev){
		list.add(ev);
	}
	public void removeEventDTO(EventDTO ev){
		list.remove(ev);
	}
	*/

}
