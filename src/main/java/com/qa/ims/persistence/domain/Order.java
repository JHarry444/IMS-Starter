package com.qa.ims.persistence.domain;

import java.util.List;
import java.util.stream.Collectors;
import com.qa.ims.persistence.domain.OrderDetail;

public class Order {

	private Long id;
	private Long custID;
	private List<Item> items;

	public Order(Long custID, List<Item> items) {
		this.setCustID(custID);
		this.setItems(items);
	}

	public Order(Long id, Long custID, List<Item> items) {
		this.setId(id);
		this.setCustID(custID);
		this.setItems(items);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public Long getCustID() {
        return this.custID;
    }

    public void setCustID(Long custID) {
        this.custID = custID;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
	public String toString() {
		return "id:" + id + " customer ID:" + custID + " items:" + String.join(", ", items.stream().map(x -> x.getName()).collect(Collectors.toList()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((custID == null) ? 0 : custID.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
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
		Order other = (Order) obj;
		if (getCustID() == null) {
			if (other.getCustID() != null)
				return false;
		} else if (!getCustID().equals(other.getCustID()))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (getItems() == null) {
			if (other.getItems() != null)
				return false;
		} else if (!getItems().equals(other.getItems()))
			return false;
		return true;
	}

}
