package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Item {

	private Long itemid;
	private String product;
	private Double price;
	
	
	public Item(String product, Double price) {
		this.setProduct(product);
		this.setPrice(price);
		
	}
	
	public Item(Long itemid, String product, Double price) {
		
		this.setItemId(itemid);
		this.setProduct(product);
		this.setPrice(price);
		
	}
	
	public Long getItemId() {
		return itemid;
	}
	
	public void setItemId(Long itemid) {
		this.itemid = itemid;
	}
	
	public String getProduct() {
		return product;
	}
	
	public void setProduct(String product) {
		this.product = product;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "itemid: " + itemid + " product: " + product + " price: " + price;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemid == null) ? 0 : itemid.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		Item other = (Item) obj;
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
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
	
	
	
	
}
