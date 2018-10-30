package com.leosal.medrep.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.leosal.dbutils.DBEntity;


/**
 * The persistent class for the event_adverts database table.
 * 
 */
@Entity
@Table(name="event_adverts")
@NamedQuery(name="EventAdvert.findAll", query="SELECT e FROM EventAdvert e")
public class EventAdvert implements DBEntity, Serializable,
	Comparable<EventAdvert>{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="gift_id")
	private int giftId;

	@Column(name="prod_id")
	private int prodId;

	private int quantity;

	//bi-directional many-to-one association to Event
	@ManyToOne
	private Event event;

	public EventAdvert() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getGiftId() {
		return this.giftId;
	}

	public void setGiftId(int giftId) {
		this.giftId = giftId;
	}

	public int getProdId() {
		return this.prodId;
	}

	public void setProdId(int prodId) {
		this.prodId = prodId;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	@Override
	public int compareTo(EventAdvert o) {
		
		return this.getId().compareTo(o.getId());
	}
	
	@Override
	 public int hashCode()
	 {
	    final int PRIME = 31;
	    int result = 1;
	    result = PRIME * result + getId().intValue();
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
        DBEntity e = (DBEntity) o;
        return (this.getId().equals(e.getId()));
	}

}