package com.qa.ims.persistence.domain;

public class Order {
	private long order_id;
	private	long item_id;
	private long customer_id;
	private long order_items_id;
	private long order_price;
	public Order(long customer_id, long item_id) {
		// TODO Auto-generated constructor stub
		this.setCustomer_id(customer_id);
		this.setItem_id(item_id);
	}
	public Order(long order_id, long customer_id, long item_id,  long order_items_id, long order_price) {
		super();
		this.order_id = order_id;
		this.item_id = item_id;
		this.customer_id = customer_id;
		this.order_items_id = order_items_id;
		this.order_price = order_price;
	}
	public Order(long order_id, long item_id, long customer_id) {
		super();
		this.order_id = order_id;
		this.item_id = item_id;
		this.customer_id = customer_id;
	}
	public long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}
	public long getItem_id() {
		return item_id;
	}
	public void setItem_id(long item_id) {
		this.item_id = item_id;
	}
	public long getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (customer_id ^ (customer_id >>> 32));
		result = prime * result + (int) (item_id ^ (item_id >>> 32));
		result = prime * result + (int) (order_id ^ (order_id >>> 32));
		result = prime * result + (int) (order_items_id ^ (order_items_id >>> 32));
		result = prime * result + (int) (order_price ^ (order_price >>> 32));
		return result;
	}
	
	
	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", item_id=" + item_id + ", customer_id=" + customer_id
				+ ", order_items_id=" + order_items_id + ", order_price=" + order_price + "]";
	}
	public long getOrder_price() {
		return order_price;
	}
	public void setOrder_price(long order_price) {
		this.order_price = order_price;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (customer_id != other.customer_id)
			return false;
		if (item_id != other.item_id)
			return false;
		if (order_id != other.order_id)
			return false;
		if (order_items_id != other.order_items_id)
			return false;
		if (order_price != other.order_price)
			return false;
		return true;
	}

	
}
