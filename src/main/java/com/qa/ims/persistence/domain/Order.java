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
	
	public String getFname() {
		return fname;
	}

	public String getSname() {
		return sname;
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

	public void setItem_name(String itemName) {
		this.itemName = itemName;
	}

	public void setItem_cost(Double itemCost) {
		this.itemCost = itemCost;
	}

	public void setItem_desc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public Order(Long orderId, Long customerId) {
		this.setOrderId(orderId);
		this.setCustomerId(customerId);
	}

	public Order(Long customerId) {
		this.setCustomerId(customerId);
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
		return "Customer ID: " + customerId + " Order ID : " + orderId;
	}


	public String itemsToString() {
		String br = Formatting.BR.getDescription();
		return br + "\nName: " + fname + " " + sname  + " | Order ID: " + orderId + "  | Item ID: " + itemId
				+ " | Qty: " + quantity + " | Item : " + itemName + " | Item : £" + itemCost + " | Description: "
				+ itemDesc + " | Total Cost: £" + totalCost;
	}
	

	

}
