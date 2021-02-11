package com.qa.ims.persistence.domain;


public class Order {

	private Long orderId;
	private Long customerId;
	private String fname;
	private String sname;
	private Long itemId;
	private Long quantity;
	private String itemName;
	private Double itemCost;
	private String itemDesc;
	private Double totalCost;

	public Order(Long orderId, String fname, String sname, Long itemId, Long quantity,
			String itemName, Double itemCost, String itemDesc, Double totalCost) {
		this.orderId = orderId;
		this.fname = fname;
		this.sname = sname;
		this.itemId = itemId;
		this.quantity = quantity;
		this.itemName = itemName;
		this.itemCost = itemCost;
		this.itemDesc = itemDesc;
		this.totalCost = totalCost;
	}

	public Order(Long itemId, Long orderId, Long quantity) {
		this.setItemId(itemId);
		this.setOrderId(orderId);
		this.setQuantity(quantity);
	} 
	
	public Order(Long orderId, Long customerId) {
		this.setOrderId(orderId);
		this.setCustomerId(customerId);
	}

	public Order(Long customerId) {
		this.setCustomerId(customerId);
	}
	
	
	public String getFname() {
		return fname;
	}

	public String getSname() {
		return sname;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setItemCost(Double itemCost) {
		this.itemCost = itemCost;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public Long getItemId() {
		return itemId;
	}

	public Long getQuantity() {
		return quantity;
	}

	public String getItemName() {
		return itemName;
	}

	public Double getItemCost() {
		return itemCost;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	

	public Long getOrderId() {
		return orderId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "Customer ID: " + customerId + " Order ID: " + orderId;
	}
	
	


	public String itemsToString() {
		String br = Formatting.BR.getDescription();
		return br + "\nName: " + fname + " " + sname  + " | Order ID: " + orderId + " | Item ID: " + itemId
				+ " | Qty: " + quantity + " | Item: " + itemName + " | Item Cost: £" + itemCost + " | Description: "
				+ itemDesc + " | Total Cost: £" + totalCost;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((fname == null) ? 0 : fname.hashCode());
		result = prime * result + ((itemCost == null) ? 0 : itemCost.hashCode());
		result = prime * result + ((itemDesc == null) ? 0 : itemDesc.hashCode());
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((sname == null) ? 0 : sname.hashCode());
		result = prime * result + ((totalCost == null) ? 0 : totalCost.hashCode());
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
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (fname == null) {
			if (other.fname != null)
				return false;
		} else if (!fname.equals(other.fname))
			return false;
		if (itemCost == null) {
			if (other.itemCost != null)
				return false;
		} else if (!itemCost.equals(other.itemCost))
			return false;
		if (itemDesc == null) {
			if (other.itemDesc != null)
				return false;
		} else if (!itemDesc.equals(other.itemDesc))
			return false;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (sname == null) {
			if (other.sname != null)
				return false;
		} else if (!sname.equals(other.sname))
			return false;
		if (totalCost == null) {
			if (other.totalCost != null)
				return false;
		} else if (!totalCost.equals(other.totalCost))
			return false;
		return true;
	}
	
	

	

}
