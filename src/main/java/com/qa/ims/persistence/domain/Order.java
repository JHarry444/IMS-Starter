package com.qa.ims.persistence.domain;

import java.util.Map;

public class Order {

	private Long orderlineid;
	private Long orderid;
    private Long id;
	private Long itemid;
	private Long itemquant;

	public Order(Long orderid, Long itemid, Long itemquant) {
		this.setOrderid(orderid);
		this.setItemid(itemid);
		this.setItemquant(itemquant);
	}
    public Order(Long orderlineid, Long id, Long orderid, Long itemid, Long itemquant) {
    	this.setOrderlineid(orderlineid);
    	this.setId(id);
    	this.setOrderid(orderid);
    	this.setItemid(itemid);
    	this.setItemquant(itemquant);
    }
	public Order (Long id, Long orderid) {
		this.setOrderid(id);
		this.setId(orderid);
	}
	
	public Order (Long orderid, Long id, Long itemid, Long itemquant) {
		this.setOrderid(orderid);
		this.setId(id);
		this.setItemid(itemid);
		this.setItemquant(itemquant);
	}
	
	
	public Order(Long id) {
		this.setId(id);
	}
	
	public Long getOrderlineid() {
		return orderlineid;
	}

	public void setOrderlineid(Long orderlineid) {
		this.orderlineid = orderlineid;
	}

	public Long getOrderid() {
		return orderid;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Long getItemid() {
		return itemid;
	}

	public void setItemid(Long itemid) {
		this.itemid = itemid;
	}

	public Long getItemquant() {
		return itemquant;
	}

	public void setItemquant(Long itemquant) {
		this.itemquant = itemquant;
	}
	
	@Override
    public String toString() {
	return " Customer ID=" + id + "Order ID=" + orderid + ", Item ID=" + itemid
			+ ", Item quantity=" + itemquant + "]"; 
}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orderid == null) ? 0 : orderid.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (orderid == null) {
			if (other.orderid != null)
				return false;
		} else if (!orderid.equals(other.orderid))
			return false;
		return true;
	}

	
	
	
}
