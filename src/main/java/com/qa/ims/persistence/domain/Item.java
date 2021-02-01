package com.qa.ims.persistence.domain;

public class Item {

	private Long id;
	private String name;
	private Double cost;
	private String description;

	public Item(Long id, String name, Double cost, String description) {
		this.setId(id);
		this.setName(name);
		this.setCost(cost);
		this.setDescription(description);
	}
	
	public Item(String name, Double cost, String description) {
		this.setName(name);
		this.setCost(cost);
		this.setDescription(description);
	}
	

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Double getCost() {
		return cost;
	}

	public String getDescription() {
		return description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return String.format("id: " + id + " Name: " + name + " Cost: " + cost + " Description: " + description);
	}

	@Override
	public int hashCode() {
		final int prime =31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		
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
		if (getName() == null) {
			if (other.getName() != null)
				return false;
		} else if (!getName().equals(other.getName()))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		
		return true;
	}

}
