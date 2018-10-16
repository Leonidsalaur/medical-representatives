package com.leosal.medrepear.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigInteger;
import java.util.Set;


/**
 * The persistent class for the adverts database table.
 * 
 */
@Entity
@Table(name="adverts") 
@NamedQuery(name="Advert.findAll", query="SELECT a FROM Advert a")
public class Advert extends com.leosal.util.jpa.Entity implements Serializable,
	Comparable<Advert>{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="bar_code")
	private BigInteger barCode;

	private String name;

	private float price;

	//bi-directional many-to-one association to EventGift
	@OneToMany(mappedBy="advert")
	private Set<EventGift> eventGifts;

	public Advert() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigInteger getBarCode() {
		return this.barCode;
	}

	public void setBarCode(BigInteger barCode) {
		this.barCode = barCode;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Set<EventGift> getEventGifts() {
		return this.eventGifts;
	}

	public void setEventGifts(Set<EventGift> eventGifts) {
		this.eventGifts = eventGifts;
	}

	public EventGift addEventGift(EventGift eventGift) {
		getEventGifts().add(eventGift);
		eventGift.setAdvert(this);

		return eventGift;
	}

	public EventGift removeEventGift(EventGift eventGift) {
		getEventGifts().remove(eventGift);
		eventGift.setAdvert(null);

		return eventGift;
	}
	
	@Override
	public int compareTo(Advert o) {
		
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
        Advert e = (Advert) o;
        return (this.getId().equals(e.getId()));
	}

}