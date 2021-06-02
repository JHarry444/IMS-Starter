package com.qa.ims.persistence.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Order {

	private Long id;
	private Long customerId;
	private LocalDate orderedOn;
	
//	CONSTRUCTORS
	public Order(Long customerId, LocalDate orderedOn) {
		this.setCustomerId(customerId);
		this.setOrderedOn(orderedOn);
	}
	
	public Order(Long customerId) {
		this.setCustomerId(customerId);
		this.setOrderedOn(LocalDate.now());
	}
	
	public Order(Long id, Long customerId) {
		this.setId(id);
		this.setCustomerId(customerId);
		this.setOrderedOn(LocalDate.now());
	}
	
	public Order(Long id, Long customerId, LocalDate orderedOn) {
		this.setId(id);
		this.setCustomerId(customerId);
		this.setOrderedOn(orderedOn);
	}
	
//	GETTERS
	public Long getId() {
		return this.id;
	}
	
	public Long getCustomerId() {
		return this.customerId;
	}
	
	public String getOrderedOnString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return orderedOn.format(formatter);
	}
	
	public LocalDate getOrderedOnDate() {
		return this.orderedOn;
	}
	
//	SETTERS
	public void setId(Long newId) {
		this.id = newId;
	}
	
	public void setCustomerId(Long newCustomerId) {
		this.customerId = newCustomerId;
	}
	
	public void setOrderedOn(LocalDate newOrderedOn) {
		this.orderedOn = newOrderedOn;
	}
	
	public void setOrderedOn(String newOrderedOn) {
		String[] dateArr = newOrderedOn.split("-", 0);
		int year = Integer.parseInt(dateArr[0]);
		int month = Integer.parseInt(dateArr[1]);
		int day = Integer.parseInt(dateArr[2]);
		this.orderedOn = LocalDate.of(year, month, day);
	}
	
//	OVERRIDES
	@Override
	public String toString() {
		return "id: " + this.getId() + ", customerId: " + this.getCustomerId() + ", orderedOn: " + this.getOrderedOnString();
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
		if (getCustomerId() == null) {
			if (other.getCustomerId() != null)
				return false;
		} else if (!getCustomerId().equals(other.getCustomerId()))
			return false;
		if (orderedOn == null) {
			if (other.orderedOn != null)
				return false;
		} else if (!orderedOn.equals(other.orderedOn))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
