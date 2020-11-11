package com.qa.ims.persistence.domain;

public class Item {
	
	private Long ID;
	private String title;
	private Double value;
	
	public Item(String title, Double value) {
		this.title = title;
		this.value = value;
	}
	
	public Item(Long ID, String title, Double value) {
		this.ID = ID;
		this.title = title;
		this.value = value;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getTitle() {
		return title;
	}

	public void setName(String name) {
		this.title = name;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "id:"+ID+" Title: "+title +" Value: "+value;
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
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
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
