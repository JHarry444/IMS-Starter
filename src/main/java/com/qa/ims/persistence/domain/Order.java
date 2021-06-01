package com.qa.ims.persistence.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Order {

	private Long id;
	private Long customerId;
	private BigDecimal total;
	private LocalDate orderedOn;
	
//	CONSTRUCTORS
	public Order(Long customerId, double total, LocalDate orderedOn) {
		this.setCustomerId(customerId);
		this.setTotal(total + "");
		this.setOrderedOn(orderedOn);
	}
	
	public Order(Long customerId, String total, LocalDate orderedOn) {
		this.setCustomerId(customerId);
		this.setTotal(total);
		this.setOrderedOn(orderedOn);
	}
	
	public Order(Long id, Long customerId, double total, LocalDate orderedOn) {
		this.setId(id);
		this.setCustomerId(customerId);
		this.setTotal(total + "");
		this.setOrderedOn(orderedOn);
	}
	
	public Order(Long id, Long customerId, String total, LocalDate orderedOn) {
		this.setId(id);
		this.setCustomerId(customerId);
		this.setTotal(total);
		this.setOrderedOn(orderedOn);
	}
	
//	GETTERS
	public Long getId() {
		return this.id;
	}
	
	public Long getCustomerId() {
		return this.customerId;
	}
	
	public String getTotalString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
		return nf.format(this.total.doubleValue());
	}
	
	public double getTotalDouble() {
		return this.total.doubleValue();
	}
	
	public String getOrderedOn() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		return orderedOn.format(formatter);
	}
	
//	SETTERS
	public void setId(Long newId) {
		this.id = newId;
	}
	
	public void setCustomerId(Long newCustomerId) {
		this.customerId = newCustomerId;
	}
	
	public void setTotal(String newTotal) {
		BigDecimal nt = new BigDecimal(newTotal);
		nt = nt.setScale(2, RoundingMode.CEILING);
		this.total = nt;
	}
	
	public void setTotal(double newTotal) {
		BigDecimal nt = new BigDecimal(newTotal + "");
		nt = nt.setScale(2, RoundingMode.CEILING);
		this.total = nt;
	}
	
	public void setOrderedOn(LocalDate newOrderedOn) {
		this.orderedOn = newOrderedOn;
	}
	
//	OVERRIDES
	@Override
	public String toString() {
		return "id: " + this.getId() + ", customerId: " + this.getCustomerId() + ", total: " + this.getTotalDouble() + ", orderedOn: " + this.getOrderedOn();
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
		if (total == null) {
			if (other.total != null)
				return false;
		} else if (!getTotalString().equals(other.getTotalString()))
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
