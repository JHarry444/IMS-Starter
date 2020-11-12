package com.qa.ims.persistence.domain;

public class Order {
	private Long ID;
	private Long CustomerID;
	//getters and setters
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public Long getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(Long customerID) {
		CustomerID = customerID;
	}
	//constructors
	public Order(Long ID, Long CustomerID) {
		this.ID = ID;
		this.CustomerID = CustomerID;
	}
	public Order(Long CustomerID) {
		this.CustomerID = CustomerID;
	}
	
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		if (CustomerID == null) {
			if (other.CustomerID != null)
				return false;
		} else if (!CustomerID.equals(other.CustomerID))
			return false;
	
		return true;
	}
	
}
