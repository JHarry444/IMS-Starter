package com.qa.ims.persistence.domain;

public class Item {
	
	private Long ID;
	private String name;
	private Double value;
	
	public Item(String name, Double value) {
		this.name = name;
		this.value = value;
	}
	
	public Item(Long ID, String name, Double value) {
		this.ID = ID;
		this.name = name;
		this.value = value;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "id:"+ID+" name: "+name +" Value: "+value;
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
		}
}
