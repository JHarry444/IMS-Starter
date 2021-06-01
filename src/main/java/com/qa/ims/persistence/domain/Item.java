package com.qa.ims.persistence.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class Item {

	private Long id;
	private String company;
	private String product;
	private BigDecimal price;
	
//	CONSTRUCTORS
	public Item(String company, String product, String price) {
		this.setCompany(company);
		this.setProduct(product);
		this.setPrice(price);
	}
	
	public Item(String company, String product, double price) {
		this.setCompany(company);
		this.setProduct(product);
		this.setPrice(price);
	}
	
	public Item(Long id, String company, String product, String price) {
		this.setId(id);
		this.setCompany(company);
		this.setProduct(product);
		this.setPrice(price);
	}
	
	public Item(Long id, String company, String product, double price) {
		this.setId(id);
		this.setCompany(company);
		this.setProduct(product);
		this.setPrice(price + "");
	}
	
//	GETTERS
	public Long getId() {
		return this.id;
	}
	
	public String getCompany() {
		return this.company;
	}
	
	public String getProduct() {
		return this.product;
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
	
	public void setCompany(String newCompany) {
		this.company = newCompany;
	}
	
	public void setProduct(String newProduct) {
		this.product = newProduct;
	}
	
	public void setPrice(String newPrice) {
		BigDecimal nt = new BigDecimal(newPrice);
		nt = nt.setScale(2, RoundingMode.CEILING);
		this.price = nt;
	}
	
	public void setPrice(double newPrice) {
		BigDecimal nt = new BigDecimal(newPrice + "");
		nt = nt.setScale(2, RoundingMode.CEILING);
		this.price = nt;
	}
	
//	OVERRIDES
	@Override
	public String toString() {
		return "id: " + this.id + ", company: " + this.company + ", product: " + this.product + ", price: " + this.price;
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
		if (getCompany() == null) {
			if (other.getCompany() != null)
				return false;
		} else if (!getCompany().equals(other.getCompany()))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
