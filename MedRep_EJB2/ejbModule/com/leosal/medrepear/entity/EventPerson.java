package com.leosal.medrepear.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the event_persons database table.
 * 
 */
@Entity
@Table(name="event_persons")
@NamedQuery(name="EventPerson.findAll", query="SELECT e FROM EventPerson e")
public class EventPerson extends com.leosal.util.jpa.Entity implements Serializable,
	Comparable<EventPerson>{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Lob
	private String comment;

	//bi-directional many-to-one association to Event
	@ManyToOne
	private Event event;

	//bi-directional many-to-one association to Person
	@ManyToOne
	private Person person;

	public EventPerson() {
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

	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	@Override
	public int compareTo(EventPerson o) {
		
		return this.getId().compareTo(o.getId());
	}
	
	@Override
	 public int hashCode()
	 {
	    final int PRIME = 31;
	    int result = 1;
	    result = PRIME * result + (getId()==null?0:getId());
	    return result;
	 }
	
	@Override
	public boolean equals(Object o) {
        if(o == null)
        {
            return false;
        }
        if (o == this)
        {
           return true;
        }
        if (getClass() != o.getClass())
        {
            return false;
        }
        com.leosal.util.jpa.Entity e = (com.leosal.util.jpa.Entity) o;
        //System.out.println("Event: "+e.getId());
        if(e.getId()==null)
        	return false;
        return (this.getId().equals(e.getId()));
	}

}