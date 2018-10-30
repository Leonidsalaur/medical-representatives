package com.leosal.medrep.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.leosal.dbutils.DBEntity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * The persistent class for the events database table.
 * 
 */
@Entity
@Table(name="events")
@NamedQuery(name="Event.findAll", query="SELECT e FROM Event e")
public class Event implements DBEntity, Serializable,
	Comparable<Event>{
	private static final long serialVersionUID = 1L;
	private static int hashGenerator = 0;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(columnDefinition = "TEXT")
	private String comment;

	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(columnDefinition = "TEXT")
	private String message;

	//bi-directional many-to-one association to EventAdvert
	@OneToMany(mappedBy="event")
	private Set<EventAdvert> eventAdverts;

	//bi-directional many-to-one association to EventGift
	@OneToMany(mappedBy="event")
	private Set<EventGift> eventGifts;

	//bi-directional many-to-one association to EventPerson
	@OneToMany(mappedBy="event")
	private Set<EventPerson> eventPersons;

	//bi-directional many-to-one association to EventProduct
	@OneToMany(mappedBy="event")
	private Set<EventProduct> eventProducts;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="ev_initiator")
	private Person person;

	//bi-directional many-to-one association to EventType
	@ManyToOne
	@JoinColumn(name="ev_type_id")
	private EventType eventType;

	public Event() {
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

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Set<EventAdvert> getEventAdverts() {
		return this.eventAdverts;
	}

	public void setEventAdverts(Set<EventAdvert> eventAdverts) {
		this.eventAdverts = eventAdverts;
	}

	public EventAdvert addEventAdvert(EventAdvert eventAdvert) {
		getEventAdverts().add(eventAdvert);
		eventAdvert.setEvent(this);

		return eventAdvert;
	}

	public EventAdvert removeEventAdvert(EventAdvert eventAdvert) {
		getEventAdverts().remove(eventAdvert);
		eventAdvert.setEvent(null);

		return eventAdvert;
	}

	public Set<EventGift> getEventGifts() {
		if(this.eventGifts==null) eventGifts = new HashSet<EventGift>();
		return this.eventGifts;
	}

	public void setEventGifts(Set<EventGift> eventGifts) {
		this.eventGifts = eventGifts;
	}

	public EventGift addEventGift(EventGift eventGift) {
		getEventGifts().add(eventGift);
		eventGift.setEvent(this);

		return eventGift;
	}

	public EventGift removeEventGift(EventGift eventGift) {
		getEventGifts().remove(eventGift);
		eventGift.setEvent(null);

		return eventGift;
	}

	public Set<EventPerson> getEventPersons() {
		if(this.eventPersons==null) this.eventPersons = new HashSet<EventPerson>();
		return this.eventPersons;
	}

	public void setEventPersons(Set<EventPerson> eventPersons) {
		this.eventPersons = eventPersons;
	}

	public EventPerson addEventPerson(EventPerson eventPerson) {
		getEventPersons().add(eventPerson);
		eventPerson.setEvent(this);

		return eventPerson;
	}

	public EventPerson removeEventPerson(EventPerson eventPerson) {
		getEventPersons().remove(eventPerson);
		eventPerson.setEvent(null);

		return eventPerson;
	}

	public Set<EventProduct> getEventProducts() {
		if(this.eventProducts==null) this.eventProducts = new HashSet<EventProduct>();
		return this.eventProducts;
	}

	public void setEventProducts(Set<EventProduct> eventProducts) {
		this.eventProducts = eventProducts;
	}

	public EventProduct addEventProduct(EventProduct eventProduct) {
		getEventProducts().add(eventProduct);
		eventProduct.setEvent(this);

		return eventProduct;
	}

	public EventProduct removeEventProduct(EventProduct eventProduct) {
		getEventProducts().remove(eventProduct);
		eventProduct.setEvent(null);

		return eventProduct;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public EventType getEventType() {
		return this.eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}
	
	@Override
	public int compareTo(Event o) {
		
		return this.getId().compareTo(o.getId());
	}
	
	@Override
	 public int hashCode()
	 {
	    final int PRIME = 31;
	    int result = 1;
	    result = (int) (PRIME * result + (getId()==null?hashGenerator++ : getId()));
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
        
        return (this.hashCode()==o.hashCode());
	}

}