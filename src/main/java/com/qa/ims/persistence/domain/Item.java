package com.qa.ims.persistence.domain;

public class Item {
	private long item_id;
	private String item_name;
	private int item_price;

	public Item(long item_id, String item_name, int item_price) {
		super();
		this.item_id = item_id;
		this.item_name = item_name;
		this.item_price = item_price;
	}

	public Item(String itemName, int itemPrice) {
		this.setItem_name(itemName);
		this.setItem_price(itemPrice);
	}

	public long getItem_id() {
		return item_id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public int getItem_price() {
		return item_price;
	}

	public void setItem_price(int item_price) {
		this.item_price = item_price;
	}

	@Override
	public String toString() {
		return "Item [item_id=" + item_id + ", item_name=" + item_name + ", item_price=" + item_price + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (item_id ^ (item_id >>> 32));
		result = prime * result + ((item_name == null) ? 0 : item_name.hashCode());
		result = prime * result + item_price;
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
		if (item_id != other.item_id)
			return false;
		if (item_name == null) {
			if (other.item_name != null)
				return false;
		} else if (!item_name.equals(other.item_name))
			return false;
		if (item_price != other.item_price)
			return false;
		return true;
	}
}
