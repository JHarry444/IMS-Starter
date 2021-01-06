package com.qa.ims.persistence.domain;

public class Customer {

	public Long customer_id;
	private String firstName;
	private String surname;

	public Customer(String firstName, String surname) {
		this.firstName = firstName;
		this.surname = surname;
	}

	public Customer(Long id, String firstName, String surname) {
		this.customer_id = id;
		this.firstName = firstName;
		this.surname = surname;
	}

	public Long getId() {
		return customer_id;
	}

	public void setId(Long id) {
		this.customer_id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "id:" + customer_id + " first name:" + firstName + " surname:" + surname;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (customer_id == null) {
			if (other.customer_id != null)
				return false;
		} else if (!customer_id.equals(other.customer_id))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

}
