package com.qa.ims.persistence.domain;

public class Orderline {

	private Long orderlineID;
	private Long orderID;
	private Long productID;
	

	public Orderline(Long orderID, Long productID) {
		this.orderID = orderID;
		this.productID = productID;
	}

	public Orderline(Long orderlineID, Long orderID, Long productID) {
		this.orderlineID = orderlineID;
		this.orderID = orderID;
		this.productID = productID;
	}	
	
	public Long getOrderlineID() {
		return orderlineID;
	}

	public void setOrderlineID(Long orderlineID) {
		this.orderlineID = orderlineID;
	}

	public Long getOrderID() {
		return orderID;
	}

	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}

	public Long getProductID() {
		return productID;
	}

	public void setProductID(Long productID) {
		this.productID = productID;
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
		if (orderID == null) {
			if (other.orderID != null)
				return false;
		} else if (!orderID.equals(other.orderID))
			return false;
		if (orderlineID == null) {
			if (other.orderlineID != null)
				return false;
		} else if (!orderlineID.equals(other.orderlineID))
			return false;
		if (productID == null) {
			if (other.productID != null)
				return false;
		} else if (!productID.equals(other.productID))
			return false;
		return true;
	}
}