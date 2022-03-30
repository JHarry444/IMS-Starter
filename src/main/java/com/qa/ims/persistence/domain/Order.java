package com.qa.ims.persistence.domain;

import java.util.ArrayList;
import java.util.Objects;

public class Order {

	private Long id;
	private Customer customer;
	private ArrayList<Item> item;
	
	public Order() {
	}
	
	public Order(Long id, Customer customer, ArrayList<Item> item) {
		this.id = id;
		this.customer = customer;
		this.item = item;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ArrayList<Item> getItem() {
		return item;
	}
	@Override
	public int hashCode() {
		return Objects.hash(customer, id, item);
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
		return Objects.equals(customer, other.customer) && Objects.equals(id, other.id)
				&& Objects.equals(item, other.item);
	}

}
