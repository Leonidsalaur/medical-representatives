package com.leosal.medrepear.dto;

import java.io.Serializable;

public class EventGiftDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private AdvertDTO advert;
	private Integer event;
	
	private int quantity;

	public EventGiftDTO() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AdvertDTO getAdvert() {
		return this.advert;
	}

	public void setAdvert(AdvertDTO advert) {
		this.advert = advert;
	}

	public Integer getEvent() {
		return this.event;
	}

	public void setEvent(Integer event) {
		this.event = event;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
