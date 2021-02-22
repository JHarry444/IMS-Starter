package com.qa.ims.persistence.domain;

public class Order {

	private Long order_id;
	private Long cust_id;
	private float subtotal;

	public Order(Long cust_id, float subtotal) {
		this.setCust_id(cust_id);
		this.setSubtotal(subtotal);
	}

	public Order(Long order_id, Long cust_id) {
		this.setOrder_id(order_id);
		this.setCust_id(cust_id);
	}

	public Order(Long order_id, Long cust_id, float subtotal) {
		super();
		this.order_id = order_id;
		this.cust_id = cust_id;
		this.subtotal = subtotal;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public Long getCust_id() {
		return cust_id;
	}

	public void setCust_id(Long cust_id) {
		this.cust_id = cust_id;
	}

	public float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}

	@Override
	public String toString() {
		return "order_id:" + order_id + "cust_id" + cust_id + "subtotal" + subtotal;
	}	

}
