package com.qa.ims.persistence.domain;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class Item {

	private Long id;
	private String name;
	private String description;
	private BigDecimal price;
	
//	CONSTRUCTOR
	public Item(Long id, String name, String description, String price) {
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
		this.setPrice(price);
	}
	
//	GETTERS
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public double getPriceDouble() {
		return this.price.doubleValue();
	}
	
	public String getPriceString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
		return nf.format(this.price.doubleValue());
	}
	
//	SETTERS
	public void setId(Long newId) {
		this.id = newId;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public void setDescription(String newDescription) {
		this.description = newDescription;
	}
	
	public void setPrice(String newPrice) {
		BigDecimal np = new BigDecimal(newPrice);
		this.price = np;
	}
	
	public void setPrice(double newPrice) {
		BigDecimal np = new BigDecimal(newPrice + "");
		this.price = np;
	}
	
//	OVERRIDES
	@Override
	public String toString() {
		return "id: " + this.id + ", name: " + this.name + ", description: " + this.description + ", price: " + this.price;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Item other = (Item) obj;
		if (getName() == null) {
			if (other.getName() != null)
				return false;
		} else if (!getName().equals(other.getName()))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}
}
