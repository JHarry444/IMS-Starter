package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class CustomerTest {
	Customer cust = new Customer( 12345L,"Charlie","Herriott");

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Customer.class).verify();
	}
  
	@Test
	public void testConstructor () {
		Customer cust = new Customer("Charlie", "Herriott");
		Customer custTwo = new Customer(121313L, "Charlie", "Herriott");
	}
	
	@Test
	public void testGetId() {
		cust.setId(123456L);
		long test = cust.getId();
		assertEquals(123456L, test);
	} 
	
	@Test
	public void testGetSurname() {
		cust.setSurname("Herr");
		assertEquals("Herr", cust.getSurname());
	}
	
	@Test
	public void testToString() {
		assertEquals("id:12345 First name:Charlie Surname:Herriott",cust.toString());

	}
	
	
}
