package com.leosal.medrepear.dto;

import java.io.Serializable;

public class EventProductDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private ProductDTO product;
	private Integer event;
	
	private int quantity;

	public EventProductDTO() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}
}
