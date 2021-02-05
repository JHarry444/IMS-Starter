package com.qa.ims.persistence.domain;

public class Order {
	
	private Long order_id;
	private Long customer_id;

	
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
		return "Customer ID: " + this.customer_id + " Order ID : " + this.order_id +  "\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer_id == null) ? 0 : customer_id.hashCode());
		result = prime * result + ((order_id == null) ? 0 : order_id.hashCode());
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
		if (customer_id == null) {
			if (other.customer_id != null)
				return false;
		} else if (!customer_id.equals(other.customer_id))
			return false;
		if (order_id == null) {
			if (other.order_id != null)
				return false;
		} else if (!order_id.equals(other.order_id))
			return false;
		return true;
	}

	
	
}
