package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class CustomerTest {
	
	private static Customer testCustomer;

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Customer.class).verify();
	}
	
	@Test
	public void firstConstructorTest() {
		testCustomer = new Customer("Peter", "Griffin");
		
		assertNotNull(testCustomer);
		assertTrue(testCustomer instanceof Customer);
		
	}
	
	@Test
	public void secondConstructorTest() {
		testCustomer = new Customer(1l, "somename", "lastname");
		assertNotNull(testCustomer);
	}
	
	@Test
	public void getIdTest() {
		Customer cus = new Customer(1l, "Blake", "Parker");
		assertSame(1l,cus.getId());
	}
	
	@Test
	public void getSurnameTest() {
		Customer cus = new Customer(1l, "Blake", "Parker");
		assertSame("Parker", cus.getSurname());
	}
	
	@Test
	public void toStringTest() {
		Customer cus = new Customer(1l, "Blake", "Parker");
		assertNotNull("id:1,firstname:Blake,surname:Parker", cus.toString());
	}

}
