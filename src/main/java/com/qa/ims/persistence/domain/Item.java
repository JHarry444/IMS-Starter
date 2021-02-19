package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Item {

	private Long itemid;
	private String product;
	private Float price;
	
	
	public Item(String product, Float price) {
		this.setProduct(product);
		this.setPrice(price);
		
	}
	
	public Item(Long itemid, String product, Float price) {
		
		this.setitemid(itemid);
		this.setProduct(product);
		this.setPrice(price);
		
	}
	
	public Long getitemid() {
		return itemid;
	}
	
	public void setitemid(Long itemid) {
		this.itemid = itemid;
	}
	
	public String getProduct() {
		return product;
	}
	
	public void setProduct(String product) {
		this.product = product;
	}
	
	public Float getPrice() {
		return price;
	}
	
	public void setPrice(Float price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "itemid: " + itemid + " product:" + product + " price:" + price;
	}
	
	@Override
	public int hashCode() {
	return Objects.hash(itemid, product, price);
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
		if (getProduct() == null) {
			if (other.getProduct() != null)
				return false;
		} else if (!getProduct().equals(other.getProduct()))
			return false;
		if (itemid == null) {
			if (other.itemid != null)
				return false;
		} else if (!itemid.equals(other.itemid))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}
	
	
	
	
}
