package com.qa.ims.persistence.domain;

import com.qa.ims.controller.Queries;

public class Order {

	private Long order_id;
	private Long customer_id;
	private String fname;
	private String sname;
	private Long item_id;
	private Long quantity;
	private String item_name;
	private Double item_cost;
	private String item_desc;
	private Double total_cost;

	public Order(Long order_id, String fname, String sname, Long item_id, Long quantity,
			String item_name, Double item_cost, String item_desc, Double total_cost) {
		this.order_id = order_id;
		this.fname = fname;
		this.sname = sname;
		this.item_id = item_id;
		this.quantity = quantity;
		this.item_name = item_name;
		this.item_cost = item_cost;
		this.item_desc = item_desc;
		this.total_cost = total_cost;
	}

	public Order(Long item_id, Long order_id, Long quantity) {
		this.setItem_id(item_id);
		this.setOrder_id(order_id);
		this.setQuantity(quantity);
	} 
	
	public String getFname() {
		return fname;
	}

	public String getSname() {
		return sname;
	}

	public Long getItem_id() {
		return item_id;
	}

	public Long getQuantity() {
		return quantity;
	}

	public String getItem_name() {
		return item_name;
	}

	public Double getItem_cost() {
		return item_cost;
	}

	public String getItem_desc() {
		return item_desc;
	}

	public Double getTotal_cost() {
		return total_cost;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public void setItem_cost(Double item_cost) {
		this.item_cost = item_cost;
	}

	public void setItem_desc(String item_desc) {
		this.item_desc = item_desc;
	}

	public void setTotal_cost(Double total_cost) {
		this.total_cost = total_cost;
	}

	public Order(Long order_id, Long customer_id) {
		this.setOrder_id(order_id);
		this.setCustomer_id(customer_id);
	}

	public Order(Long customer_id) {
		this.setCustomer_id(customer_id);
	}

	public Long getOrder_id() {
		return order_id;
	}

	public Long getCustomer_id() {
		return customer_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

	@Override
	public String toString() {
		return "Customer ID: " + customer_id + " Order ID : " + order_id;
	}


	public String itemsToString() {
		String br = Formatting.BR.getDescription();
		return br + "\nName: " + fname + " " + sname  + " | Order ID: " + order_id + "  | Item ID: " + item_id
				+ " | Qty: " + quantity + " | Item : " + item_name + " | Item : £" + item_cost + " | Description: "
				+ item_desc + " | Total Cost: £" + total_cost;
	}
	

	

}
