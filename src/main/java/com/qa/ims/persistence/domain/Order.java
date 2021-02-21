package com.qa.ims.persistence.domain;

public class Order {

	private Long orderid;
	private Long id;
	private Long orderlineid;
	
	public Order(Long id, Long orderlineid ) {
		this.setId(id);
		this.setOrderLineId(orderlineid);
		
	}
	
	public Order(Long orderid, Long id, Long orderlineid) {
		this.setOrderId(orderid);
		this.setId(id);
		this.setOrderLineId(orderlineid);
		
	}

	public Long getOrderId() {
		return orderid;
	}

	public void setOrderId(Long orderid) {
		this.orderid = orderid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderLineId() {
		return orderlineid;
	}

	public void setOrderLineId(Long orderlineid) {
		this.orderlineid = orderlineid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Order other = (Order) obj;
		if (id != other.id)
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
