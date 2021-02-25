package com.qa.ims.persistence.domain;

public class Orderline {

	private Long orderlineid;
	private Long orderid;
    private Long id;
    
	private Long itemid;
	private Long itemquant;

	public Orderline(Long orderid, Long itemid, Long itemquant) {
		this.setOrderid(orderid);
		this.setItemid(itemid);
		this.setItemquant(itemquant);
	}
	public Orderline(Long id, Long orderid, Long itemid, Long itemquant) {
		this.setId(id);
		this.setOrderid(orderid);
		this.setItemid(itemid);
		this.setItemquant(itemquant);
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
		result = prime * result + ((itemquant == null) ? 0 : itemquant.hashCode());
		result = prime * result + ((itemid == null) ? 0 : itemid.hashCode());
		result = prime * result + ((orderid == null) ? 0 : orderid.hashCode());
		result = prime * result + ((orderlineid == null) ? 0 : orderlineid.hashCode());
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
		Orderline other = (Orderline) obj;
		if (itemquant == null) {
			if (other.itemquant != null)
				return false;
		} else if (!itemquant.equals(other.itemquant))
			return false;
		if (itemid == null) {
			if (other.itemid != null)
				return false;
		} else if (!itemid.equals(other.itemid))
			return false;
		if (orderid == null) {
			if (other.orderid != null)
				return false;
		} else if (!orderid.equals(other.orderid))
			return false;
		if (orderlineid == null) {
			if (other.orderlineid != null)
				return false;
		} else if (!orderlineid.equals(other.orderlineid))
			return false;
		return true;
	}
	
	
}
