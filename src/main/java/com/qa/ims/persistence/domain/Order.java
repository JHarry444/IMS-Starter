package com.qa.ims.persistence.domain;

public class Order {
	
	private Long order_id;
	private Long customer_id;
	private Long item_id;
	private Long quantity;
	private Long price;

	public Order(Long order_id, Long customer_id, Long item_id, Long quantity, Long price) {
		super();
		this.order_id = order_id;
		this.customer_id = customer_id;
		this.item_id = item_id;
		this.quantity = quantity;
		this.price = price;
	}
	public Order(Long customer_id, Long item_id, Long quantity, Long price) {
		super();
		this.customer_id = customer_id;
		this.item_id = item_id;
		this.quantity = quantity;
		this.price = price;
	}
	
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public Long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

	public Long getItem_id() {
		return item_id;
	}

	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
}
