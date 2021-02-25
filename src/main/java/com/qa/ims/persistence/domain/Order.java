package com.qa.ims.persistence.domain;

public class Order {

	private Long id;
	private Long custID;

	public Order(Long custID) {
		this.setCustID(custID);
	}

	public Order(Long id, Long custID) {
		this.setId(id);
		this.setCustID(custID);
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

    @Override
	public String toString() {
		return "id:" + id + " customer ID:" + custID;
	}

	public String formattedString() {
		return "ID: " + id + ", Customer ID: " + custID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((custID == null) ? 0 : custID.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		return true;
	}

}
