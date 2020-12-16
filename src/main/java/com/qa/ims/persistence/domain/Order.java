package com.qa.ims.persistence.domain;

import java.util.List;

public class Order {

	private Long order_id;
	private Long customer_id;
	// List of items in the order
	private List<Item> items;
	
	// Generator for creating new orders
	public Order(Long customer_id, List<Item> items) {
		this.customer_id = customer_id;
		this.items = items;
	}
	
	// Generator for updating existing orders
	public Order(Long order_id, Long customer_id, List<Item> items) {
		this.order_id = order_id;
		this.customer_id = customer_id;
		this.items = items;
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

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	// Returns a string containing names of items and the number of each ordered
	@Override
	public String toString() {
		String orderDetails = "Order id: " + order_id + ", customer id: " + customer_id;
		for(Item i: items) {
			orderDetails += "\nItem: "+i.getName()+" ("+i.getQuantity()+")";
		}
		return orderDetails;
	}
	
}
