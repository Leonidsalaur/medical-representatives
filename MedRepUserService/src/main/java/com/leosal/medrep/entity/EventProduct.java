package com.leosal.medrep.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.leosal.dbutils.DBEntity;


/**
 * The persistent class for the event_products database table.
 * 
 */
@Entity
@Table(name="event_products")
@NamedQuery(name="EventProduct.findAll", query="SELECT e FROM EventProduct e")
public class EventProduct implements DBEntity, Serializable,
		Comparable<EventProduct>{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	//bi-directional many-to-one association to Event
	@ManyToOne
	@JoinColumn(name = "event_id", referencedColumnName = "id")
	private Event event;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product product;
	
	private int quantity;

	public EventProduct() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public int compareTo(EventProduct o) {
		
		return this.getId().compareTo(o.getId());
	}
	
	@Override
	 public int hashCode()
	 {
	    final int PRIME = 31;
	    int result = 1;
	    result = (int) (PRIME * result + (getId()==null?0:getId()));
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
        EventProduct e = (EventProduct) o;
        return (this.getEvent().equals(e.getEvent())&&this.getProduct().equals(e.getProduct()));
	}

}