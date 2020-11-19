package com.qa.ims.persistence.domain;

public class Orders {
	private Long order_id;
	private Long order_item_id;
	private Double unit_price; 
	private int quantity; 
	private Item item;
	private int item_id;
	private Customer customer;
	
	public Orders(Long order_id, Long order_item_id, Double unit_price, 
			int quantity, Item item, Customer customer) {
		super();
		this.order_id = order_id;
		this.order_item_id = order_item_id;
		this.unit_price = unit_price;
		this.quantity = quantity;
		this.item = item;
		this.customer = customer;
	}

	public Orders(Long order_item_id, Double unit_price, int quantity, Item item, int item_id, Customer customer) {
		super();
		this.order_item_id = order_item_id;
		this.unit_price = unit_price;
		this.quantity = quantity;
		this.item = item;
		this.item_id = item_id;
		this.customer = customer;
	}
	
	
	public Orders(Long order_id, Long order_item_id, Double unit_price, int quantity) {
		super();
		this.order_id = order_id;
		this.order_item_id = order_item_id;
		this.unit_price = unit_price;
		this.quantity = quantity;
	}
	
	

	public Orders(Long order_id, Long cust_id) {
		super();
		this.order_id = order_id;
		this.customer = new Customer(cust_id, null, null);
	}

	public Orders(Customer customer) {
		this.customer = customer;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public Long getOrder_item_id() {
		return order_item_id;
	}

	public void setOrder_item_id(Long order_item_id) {
		this.order_item_id = order_item_id;
	}

	public Double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(Double unit_price) {
		this.unit_price = unit_price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Orders [order_id=" + order_id + ", order_item_id=" + order_item_id + ", unit_price=" + unit_price
				+ ", quantity=" + quantity + ", item_id=" + item_id + ", customer=" + customer + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orders other = (Orders) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (item_id != other.item_id)
			return false;
		if (order_id == null) {
			if (other.order_id != null)
				return false;
		} else if (!order_id.equals(other.order_id))
			return false;
		if (order_item_id == null) {
			if (other.order_item_id != null)
				return false;
		} else if (!order_item_id.equals(other.order_item_id))
			return false;
		if (quantity != other.quantity)
			return false;
		if (unit_price == null) {
			if (other.unit_price != null)
				return false;
		} else if (!unit_price.equals(other.unit_price))
			return false;
		return true;
	}
	
	
}
