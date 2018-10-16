package com.leosal.medrepear.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Set;


/**
 * The persistent class for the event_types database table.
 * 
 */
@Entity
@Table(name="event_types")
@NamedQuery(name="EventType.findAll", query="SELECT e FROM EventType e")
public class EventType extends com.leosal.util.jpa.Entity implements Serializable,
		Comparable<EventType>{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private byte groupEvent;

	private String name;

	//bi-directional many-to-one association to Event
	@OneToMany(mappedBy="eventType")
	private Set<Event> events;

	public EventType() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte getGroupEvent() {
		return this.groupEvent;
	}

	public void setGroupEvent(byte groupEvent) {
		this.groupEvent = groupEvent;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Event> getEvents() {
		return this.events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public Event addEvent(Event event) {
		getEvents().add(event);
		event.setEventType(this);

		return event;
	}

	public Event removeEvent(Event event) {
		getEvents().remove(event);
		event.setEventType(null);

		return event;
	}
	
	@Override
	public int compareTo(EventType o) {
		
		return this.getId().compareTo(o.getId());
	}
	
	@Override
	 public int hashCode()
	 {
	    final int PRIME = 31;
	    int result = 1;
	    result = PRIME * result + getId();
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
        return (this.getId().equals(e.getId()));
	}

}