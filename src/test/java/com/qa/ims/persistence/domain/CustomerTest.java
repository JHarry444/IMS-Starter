package com.qa.ims.persistence.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import nl.jqno.equalsverifier.EqualsVerifier;

public class CustomerTest {

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Customer.class).verify();
	}

	@Test
	public void testCustomer() {
		Customer cust = new Customer("patrick", "star");
		assertNotNull(cust);
		assertEquals("patrick", cust.getFirstName());
		assertEquals("star", cust.getSurname());
	}

	@Test
	public void testCustomerWithID() {
		Customer cust = new Customer(1L, "spongebob", "squarepants");
		assertNotNull(cust);
		assertEquals("spongebob", cust.getFirstName());
		assertEquals("squarepants", cust.getSurname());
	}

	@Test
	public void testGetId() {
		Customer cust = new Customer(1L, "spongebob", "squarepants");
		assertEquals(1L, cust.getId());
	}

	@Test
	public void testSetId() {
		Customer cust = new Customer(1L, "spongebob", "squarepants");
		cust.setId(2L);
		assertEquals(2L, cust.getId());
	}

	@Test
	public void testGetFirstName() {
		Customer cust = new Customer(1L, "spongebob", "squarepants");
		assertEquals("spongebob", cust.getFirstName());
	}

	@Test
	public void testSetFirstName() {
		Customer cust = new Customer(1L, "spongebob", "squarepants");
		cust.setFirstName("patrick");
		assertEquals("patrick", cust.getFirstName());
	}

	@Test
	public void testGetSurname() {
		Customer cust = new Customer(1L, "spongebob", "squarepants");
		assertEquals("squarepants", cust.getSurname());
	}

	@Test
	public void testSetSurname() {
		Customer cust = new Customer(1L, "spongebob", "squarepants");
		cust.setSurname("star");
		assertEquals("star", cust.getSurname());
	}

	@Test
	public void testGetString() {
		Customer cust = new Customer(1L, "spongebob", "squarepants");
		assertEquals("id:" + cust.getId() + " first name:" + cust.getFirstName() + " surname:" + cust.getSurname(), cust.toString());
	}

	@Test
	public void testGetFormattedString() {
		Customer cust = new Customer(1L, "spongebob", "squarepants");
		assertEquals("ID: " + cust.getId() + ", First Name: " + cust.getFirstName() + ", Surname: " + cust.getSurname(), cust.formattedString());
	}
}
