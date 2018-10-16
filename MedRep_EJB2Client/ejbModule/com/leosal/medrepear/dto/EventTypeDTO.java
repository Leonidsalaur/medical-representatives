package com.leosal.medrepear.dto;

import java.io.Serializable;



public class EventTypeDTO implements Serializable, Comparable<EventTypeDTO> {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private boolean groupEvent;
	private String name;

	

	public EventTypeDTO() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isGroupEvent() {
		return this.groupEvent;
	}

	public void setGroupEvent(boolean groupEvent) {
		this.groupEvent = groupEvent;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(EventTypeDTO another) {
		return this.getId().compareTo(another.getId());
	}
	
	@Override
	public String toString(){
		return this.getName();
	}

	

}
