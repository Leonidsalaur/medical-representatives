package com.leosal.medrepear.dto;

import java.io.Serializable;


public class EventPersonDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Integer id;

	
	private String comment;

	private Integer event;


	private ContactDTO person;

	public EventPersonDTO() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getEvent() {
		return this.event;
	}

	public void setEvent(Integer event) {
		this.event = event;
	}

	public ContactDTO getPerson() {
		return this.person;
	}

	public void setPerson(ContactDTO person) {
		this.person = person;
	}
	
	public String toString(){
		String s="";
		if(getPerson()!=null){
			s+=getPerson();
			if(getPerson().getInstitutions()!=null&&getPerson().getInstitutions().size()>0)
				s+=" - "+getPerson().getInstitutions().iterator().next();
			
			return s.trim();
		}
		return super.toString();
	}
}
