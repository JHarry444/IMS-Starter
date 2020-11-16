package com.qa.ims.persistence.domain;

public class Order {
	private Long ID;
	private Long CustomerID;
	private String Customer;
	private String Items;
	private long Total;
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
	public String getCustomer() {
		return Customer;
	}
	public void setCustomer(String customer) {
		Customer =customer;
	}
	
	//constructors
	public Order(Long ID, Long CustomerID) {
		this.ID = ID;
		this.CustomerID = CustomerID;
	}
	public Order(Long CustomerID) {
		this.CustomerID = CustomerID;
	}
	public Order(Long ID, Long CustomerID, String Customer, String Items, Long Total) {
		this.ID = ID;
		this.CustomerID = CustomerID;
		this.Customer = Customer;
		this.Items = Items;
		this.Total = Total;
	}
	
	@Override
	public String toString() {
		return "ID:"+ID+" CustomerID: "+CustomerID+" Customer: "+Customer+" Items: "+Items+" Total: £"+Total;
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
