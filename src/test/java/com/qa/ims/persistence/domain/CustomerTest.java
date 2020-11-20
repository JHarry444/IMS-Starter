package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CustomerTest {
	
	private Customer testCustomer = new Customer(1l,"piers","barber");
	private Customer testCustomer2 = new Customer(1l,"piers","barber");
	private Customer testCustomer3 = new Customer(2l,"piers","barber");
	private Customer nullCustomer1 = new Customer(null,null,null);
	private Customer nullCustomer2 = new Customer(1l,null,null);
	private Customer nullCustomer3 = new Customer(1l,"piers",null);
	private Item testItem = new Item("chocolate",2.99,100l);
	
	@Test
	public void testSetters() {
		testCustomer.setId(2l);
		testCustomer.setFirstName("Aswene");
		testCustomer.setSurname("NoName");
		assertFalse(testCustomer.equals(testCustomer2));
	}
	
	@Test
	public void testGetters() {
		long id = 1l;
		String fName = "piers";
		String lName = "barber";
		assertTrue(testCustomer.getId().equals(id));
		assertTrue(testCustomer.getFirstName().equals(fName));
		assertTrue(testCustomer.getSurname().equals(lName));
	}
	
	@Test
	public void testEquals() {
		assertFalse(nullCustomer1.equals(testCustomer));
		assertFalse(nullCustomer2.equals(testCustomer));
		assertFalse(nullCustomer3.equals(testCustomer));
		assertFalse(testCustomer.equals(nullCustomer1));
		assertFalse(testCustomer.equals(nullCustomer2));
		assertFalse(testCustomer.equals(nullCustomer3));
		assertTrue(testCustomer.equals(testCustomer));
		assertFalse(testCustomer.equals(null));
		assertFalse(testCustomer.equals(testItem));
		assertFalse(testCustomer.equals(testCustomer3));
	}
}
