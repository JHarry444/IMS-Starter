package com.qa.ims.persistence.domain;

public class Item {
	private Long item_id;
	private String  item_name;
	private Float price;
	private Long quantity;
	public Item(String item_name, float price, long quantity) {
		super();
		this.item_name = item_name;
		this.price = price;
		this.quantity = quantity;
	}
	public Item(long item_id, String item_name, float price, long quantity) {
		super();
		this.item_id = item_id;
		this.item_name = item_name;
		this.price = price;
		this.quantity = quantity;
	}
	public Long getItem_id() {
		return item_id;
	}
	public void setItem_id(long item_id) {
		this.item_id = item_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "id:" + item_id + " item name:" + item_name + " price:" + price + " quantity:" + quantity;
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
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (quantity == null) {
			if (other.price != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		return true;
	}


}
