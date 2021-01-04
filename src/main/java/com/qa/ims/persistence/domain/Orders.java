package com.qa.ims.persistence.domain;

import java.math.BigDecimal;

public class Orders {

	private Long orderID;
	private Long customerID;
	private Long productID;
	private Double orderQty;
	private BigDecimal totalPrice;

	public Orders(Long customerID, Long productID, Double orderQty, BigDecimal totalPrice) {
		this.customerID = customerID;
		this.productID = productID;
		this.orderQty = orderQty;
		this.totalPrice = totalPrice;
	}

	public Orders(Long orderID, Long customerID, Long productID, Double orderQty, BigDecimal totalPrice) {
		this.orderID = orderID;
		this.customerID = customerID;
		this.productID = productID;
		this.orderQty = orderQty;
		this.totalPrice = totalPrice;
	}
	
	public Long getOrderID() {
		return orderID;
	}

	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}

	public Long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}

	public Long getProductID() {
		return productID;
	}

	public void setProductID(Long productID) {
		this.productID = productID;
	}

	public Double getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(Double orderQty) {
		this.orderQty = orderQty;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "Order ID:" + orderID + " Customer ID:" + customerID + " Product ID:" + productID + " Order Qty:" + orderQty + "Total Price:" + totalPrice;
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
		if (customerID == null) {
			if (other.customerID != null)
				return false;
		} else if (!customerID.equals(other.customerID))
			return false;
		if (orderID == null) {
			if (other.orderID != null)
				return false;
		} else if (!orderID.equals(other.orderID))
			return false;
		if (productID == null) {
			if (other.productID != null)
				return false;
		} else if (!productID.equals(other.productID))
			return false;
		if (orderQty == null) {
			if (other.orderQty != null)
				return false;
		} else if (!orderQty.equals(other.orderQty))
			return false;
		if (totalPrice == null) {
			if (other.totalPrice != null)
				return false;
		} else if (!totalPrice.equals(other.totalPrice))
			return false;
		return true;
	}
}
