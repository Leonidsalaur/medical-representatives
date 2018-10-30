package com.leosal.medrep.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.leosal.dbutils.DBEntity;


/**
 * The persistent class for the event_gifts database table.
 * 
 */
@Entity
@Table(name="event_gifts")
@NamedQuery(name="EventGift.findAll", query="SELECT e FROM EventGift e")
public class EventGift implements DBEntity, Serializable,
	Comparable<EventGift>{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	//bi-directional many-to-one association to Advert
	@ManyToOne
	@JoinColumn(name = "advert_id", referencedColumnName = "id")
	private Advert advert;

	//bi-directional many-to-one association to Event
	@ManyToOne
	@JoinColumn(name = "event_id", referencedColumnName = "id")
	private Event event;
	
	private int quantity;

	public EventGift() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Advert getAdvert() {
		return this.advert;
	}

	public void setAdvert(Advert advert) {
		this.advert = advert;
	}

	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public int compareTo(EventGift o) {
		
		return this.getId().compareTo(o.getId());
	}
	
	@Override
	 public int hashCode()
	 {
	    final int PRIME = 31;
	    int result = 1;
	    result = (int) (PRIME * result + (getId()==null ? 0 : getId()));
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
        EventGift e = (EventGift) o;
        return (this.getEvent().equals(e.getEvent())&&this.getAdvert().equals(e.getAdvert()));
	}

}