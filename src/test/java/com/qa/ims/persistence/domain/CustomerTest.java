package com.qa.ims.persistence.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

//	CONSTRUCTORS
	@Test
	public void constructorTest1() {
		Customer c = new Customer((long) 5, "Bob", "Smith");
		assertEquals(5, c.getId());
		assertEquals("Bob", c.getFirstName());
		assertEquals("Smith", c.getSurname());
	}
	
	@Test
	public void constructorTest2() {
		Customer c = new Customer("Al", "Johnson");
		assertNull(c.getId());
		assertEquals("Al", c.getFirstName());
		assertEquals("Johnson", c.getSurname());
	}
	
//	SETTERS
	@Test
	public void settersTest() {
		Customer c = new Customer("Jack", "Robinson");
		assertNull(c.getId());
		c.setId((long) 23);
		assertEquals(23, c.getId());
		assertEquals("Jack", c.getFirstName());
		c.setFirstName("Jackie");
		assertEquals("Jackie", c.getFirstName());
		assertEquals("Robinson", c.getSurname());
		c.setSurname("Biggs");
		assertEquals("Biggs", c.getSurname());
	}
	
//	OVERRIDES
	@Test
	public void toStringTest() {
		Customer c = new Customer("Will", "Johnson");
		String s = "id:null first name:Will surname:Johnson";
		assertEquals(s, c.toString());
	}
	
	@Test void hashCodeTest() {
		Customer c = new Customer("Jessie", "Jones");
		Customer c2 = new Customer("Bobby", "James");
		assertFalse(c.hashCode() == c2.hashCode());
	}
	
	@Test void equalsTest() {
		Customer c1 = new Customer((long) 3, "James", "Jackson");
		Customer c2 = new Customer((long) 4, "Susie", "Jones");
		assertFalse(c1.equals(c2));
		
		Customer c3 = new Customer("Pete", "Ratburn");
		Customer c4 = new Customer("Chuck", "Robinson");
		assertFalse(c3.equals(c4));
		c3.setFirstName("Chuck");
		assertFalse(c3.equals(c4));
		c3.setSurname("Robinson");
		assertTrue(c3.equals(c4));
		
	}

}
