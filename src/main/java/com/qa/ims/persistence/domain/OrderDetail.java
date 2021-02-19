package com.qa.ims.persistence.domain;

import java.util.List;

public class OrderDetail {

	private Long id;
	private Long orderID;
	private Long itemID;
    private Integer quantity;

	public OrderDetail(Long orderID, Long itemID, Integer quantity) {
		this.setOrderID(orderID);
		this.setItemID(itemID);
        this.setQuantity(quantity);
	}

	public OrderDetail(Long id, Long orderID, Long itemID, Integer quantity) {
        this.setID(id);
		this.setOrderID(orderID);
		this.setItemID(itemID);
        this.setQuantity(quantity);
	}

	public Long getID() {
		return id;
	}

	public void setID(Long id) {
		this.id = id;
	}

    public Long getOrderID() {
        return this.orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public Long getItemID() {
        return this.itemID;
    }

    public void setItemID(Long itemID) {
        this.itemID = itemID;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
  
    @Override
	public String toString() {
		return "id:" + id + " order ID:" + orderID + " item ID:" + itemID + " quantity:" + quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderID == null) ? 0 : orderID.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((itemID == null) ? 0 : itemID.hashCode());
        result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
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
		OrderDetail other = (OrderDetail) obj;
		if (getOrderID() == null) {
			if (other.getOrderID() != null)
				return false;
		} else if (!getOrderID().equals(other.getOrderID()))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
