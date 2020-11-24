package com.qa.ims.persistence.domain;

import java.util.List;

public class Order {

	private Long id;
	private Long customer_id;
	private List<Item> items;

	public Order(Long id, Long customer_id, List<Item> items) {
		super();
		this.id = id;
		this.customer_id = customer_id;
		this.items = items;
	}

	public Order(Long customer_id) {
		super();
		this.id = 0L;
		this.customer_id = customer_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setItem(List<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		String lines = "\n";
		double totalPrice = 0.0;
		for (Item item : items) {
			totalPrice += item.getPrice();
			lines += item.toString() + " \n";
		}
		return "Order [id=" + id + ", customer_id=" + customer_id + ", Items: " + lines + " Total Price: £" + totalPrice + "]";
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		return true;
	}

}
