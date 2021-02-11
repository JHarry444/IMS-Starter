package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class ItemTest {
	
	Item i = new Item(12345L, "toothbrush", 15.0, "Teeth scrubber");
	
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Item.class).verify();
	}
	
	@Test
	public void testConstructor() {
		Item iTest = new Item("Charlie", 18.5, "Tall man");
		Double testCost = iTest.getCost();
		assertEquals(18.5, testCost,1);
		assertEquals("Tall man", iTest.getDescription());
	}
	 
	@Test
	public void testGetId() {
		i.setId(123L);
		long test = i.getId();
		assertEquals(123L, test);
	}
	
	@Test
	public void testGetName() {
		i.setName("Mouse");
		assertEquals("Mouse", i.getName());
	}
	

	@Test
	public void testToString(){
		assertEquals("id: 12345 Name: toothbrush Cost: 15.00 Description: Teeth scrubber", i.toString());
	} 

}
