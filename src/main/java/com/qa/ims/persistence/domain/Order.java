package com.qa.ims.persistence.domain;

public class Order {

	private Long order_id;
	private int cust_id;
	private Long item_id;
	private int quantity;
	private float total_cost;
	
	public Order(int cust_id, Long item_id, int quantity) {
		this.setCust_id(cust_id);
		this.setItem_id(item_id);
		this.setQuantity(quantity);
	}
	
	public Order(Long order_id, Long item_id, int quantity) {
		this.setOrder_id(order_id);
		this.setItem_id(item_id);
		this.setQuantity(quantity);
	}
	
	public Order(Long item_id, int quantity) {
		this.setItem_id(item_id);
		this.setQuantity(quantity);
	}
	
	public Order(Long item_id, int quantity, float total_cost) {
		this.setItem_id(item_id);
		this.setQuantity(quantity);
		this.setTotal_price(total_cost);
	}
	
	public Order(Long order_id, int cust_id, Long item_id, int quantity, float total_cost) {
		super();
		this.order_id = order_id;
		this.cust_id = cust_id;
		this.item_id = item_id;
		this.quantity = quantity;
		this.total_cost = total_cost;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public int getCust_id() {
		return cust_id;
	}

	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
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

	public float getTotal_cost() {
		return total_cost;
	}

	public void setTotal_price(float total_cost) {
		this.total_cost = total_cost;
	}

	@Override
	public String toString() {
		return "order_id: " + order_id  + " ||Customer with customer id:" + cust_id + "|| purchases item_id: " + item_id  + "|| of quantity: " + quantity + "|| and the total_cost is: "+ total_cost;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cust_id;
		result = prime * result + ((item_id == null) ? 0 : item_id.hashCode());
		result = prime * result + ((order_id == null) ? 0 : order_id.hashCode());
		result = prime * result + quantity;
		result = prime * result + Float.floatToIntBits(total_cost);
		return result;
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
		if (cust_id != other.cust_id)
			return false;
		if (item_id == null) {
			if (other.item_id != null)
				return false;
		} else if (!item_id.equals(other.item_id))
			return false;
		if (order_id == null) {
			if (other.order_id != null)
				return false;
		} else if (!order_id.equals(other.order_id))
			return false;
		if (quantity != other.quantity)
			return false;
		if (Float.floatToIntBits(total_cost) != Float.floatToIntBits(other.total_cost))
			return false;
		return true;
	}
	
	

}
