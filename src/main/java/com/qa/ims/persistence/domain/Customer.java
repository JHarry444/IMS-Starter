package com.qa.ims.persistence.domain;

public class Customer {

	private Long id;
	private String firstName;
	private String surname;
	private String email; 

	public Customer(String firstName, String surname, String email) {
		this.firstName = firstName;
		this.surname = surname;
		this.email = email;
	}

	public Customer(Long id, String firstName, String surname, String email) {
		this.id = id;
		this.firstName = firstName;
		this.surname = surname;
		this.email = email;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public String getEmail() {
		return email;
		
	}
	
	public void setEmail(String email) {
		this.email = email;
		
	}

	@Override
	public String toString() {
		return "id:" + id + " first name:" + firstName + " surname:" + surname + " email:" + email;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
		
	}

}
