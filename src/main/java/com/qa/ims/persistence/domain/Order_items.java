package com.qa.ims.persistence.domain;

public class Order_items {
	
	private Long order_items_id;
	private Long item_id;
	private int quantity;
	private Long order_id;
	
	public Order_items(Long item_id, int quantity, Long order_id) {
		this.setItem_id(item_id);
		this.setQuantity(quantity);
		this.setOrder_id(order_id);
	}
	
	// array of items to pass back to orders
	
	
	public Order_items(Long item_id, int quantity) {
		this.setItem_id(item_id);
		this.setQuantity(quantity);
	}
	
	public Order_items(Long order_items_id, Long item_id, int quantity, Long order_id) {
		super();
		this.order_items_id = order_items_id;
		this.item_id = item_id;
		this.quantity = quantity;
		this.order_id = order_id;
	}
	
	public Long getOrder_items_id() {
		return order_items_id;
	}
	public void setOrder_items_id(Long order_items_id) {
		this.order_items_id = order_items_id;
	}
	public Long getItem_id() {
		return item_id;
	}
	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	@Override
	public String toString() {
		return "order_items_id:" + order_items_id + "item_id" + item_id + "quantity" + quantity + "order_id" + order_id;
	}	
}
