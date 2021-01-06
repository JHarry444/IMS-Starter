package com.qa.ims.persistence.domain;

import java.util.List;

public class Order {
	private Long order_id;
	private Long customer_id;
	private Long order_item_id;
	private Long item_id;
	private List<Item> items;
	

	public Order(Long order_id, Long order_item_id, Long item_id, List<Item> items) {
		super();
		this.order_id = order_id;
		this.order_item_id = order_item_id;
		this.item_id = item_id;
		this.items = items;
	}
	public Order(Long order_id, Long item_id, List<Item> items) {
		super();
		this.order_id = order_id;
		this.item_id = item_id;
		this.items = items;
	}
	public Order(Long order_id, Long customer_id) {
		super();
		this.order_id = order_id;
		this.customer_id = customer_id;
	}
	public Order(Long customer_id) {
		super();
		this.customer_id = customer_id;
	}
	
	public Long getOrder_id() {
		
		return order_id;
	}
	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}
	public Long getItem_id() {
		return item_id;
	}
	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}
	public Long getOrder_item_id() {
		return order_item_id;
	}
	public void setOrder_item_id(Long order_item_id) {
		this.order_item_id = order_item_id;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public Long getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}
	@Override
	public String toString() {
		String str = "";
		double price = 0.0;
		
		for (Item item : items) {
			price += item.getPrice();
			str += item.toString();
		}
		return "Here is your order: [order_id=" + order_id + ", item_id=" + item_id + ", items=" + items
			 + str + " The total price is: " + price + "]";
		
		
		
	}

}
