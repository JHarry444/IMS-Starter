package com.qa.ims.persistence.domain;

public class OrderItems {
	private String fname;
	private String sname;
	private Long item_id;
	private Long order_id;
	private Long quantity;
	private String item_name;
	private Double item_cost;
	private String item_desc;
	private Double total_cost;
	
	public OrderItems(Long item_id, Long order_id, Long quantity) {
		this.setItem_id(item_id);
		this.setOrder_id(order_id);
		this.setQuantity(quantity);
	} 

	public OrderItems(String fname, String sname, Long order_id, Long item_id, Long quantity, String item_name, Double item_cost, Double total_cost, String item_desc) {
		this.setFname(fname);
		this.setSname(sname);
		this.setOrder_id(order_id);
		this.setItem_id(item_id);
		this.setQuantity(quantity);
		this.setItem_name(item_name);
		this.setItem_cost(item_cost);
		this.setTotal_cost(total_cost);
		this.setItem_desc(item_desc);
	}
	
	public Long getItem_id() {
		return item_id;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public String getItem_name() {
		return item_name;
	}

	public Double getItem_cost() {
		return item_cost;
	}

	public String getItem_desc() {
		return item_desc;
	}

	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public void setItem_cost(Double item_cost) {
		this.item_cost = item_cost;
	}

	public void setItem_desc(String item_desc) {
		this.item_desc = item_desc;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	public String getFname() {
		return fname;
	}

	public String getSname() {
		return sname;
	}

	public Double getTotal_cost() {
		return total_cost;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public void setTotal_cost(Double total_cost) {
		this.total_cost = total_cost;
	}
	
	
	@Override
	public String toString() {
		return "Name: " + fname + " " + sname  + " Order ID: " + order_id + " Item ID: " + item_id
				+ " Quantity: " + quantity + " Item Name: " + item_name + " Item Cost: " + item_cost + " Item Description: "
				+ item_desc + " Total Cost: " + total_cost;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item_cost == null) ? 0 : item_cost.hashCode());
		result = prime * result + ((item_desc == null) ? 0 : item_desc.hashCode());
		result = prime * result + ((item_id == null) ? 0 : item_id.hashCode());
		result = prime * result + ((item_name == null) ? 0 : item_name.hashCode());
		result = prime * result + ((order_id == null) ? 0 : order_id.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
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
		OrderItems other = (OrderItems) obj;
		if (item_cost == null) {
			if (other.item_cost != null)
				return false;
		} else if (!item_cost.equals(other.item_cost))
			return false;
		if (item_desc == null) {
			if (other.item_desc != null)
				return false;
		} else if (!item_desc.equals(other.item_desc))
			return false;
		if (item_id == null) {
			if (other.item_id != null)
				return false;
		} else if (!item_id.equals(other.item_id))
			return false;
		if (item_name == null) {
			if (other.item_name != null)
				return false;
		} else if (!item_name.equals(other.item_name))
			return false;
		if (order_id == null) {
			if (other.order_id != null)
				return false;
		} else if (!order_id.equals(other.order_id))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		return true;
	}
	
	
	
	
}
