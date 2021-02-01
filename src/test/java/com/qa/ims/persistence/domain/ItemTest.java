package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class ItemTest {
	
	Item i = new Item(12345L, "toothbrush", 15.0, "Teeth scrubber");
	
	@Test
	public void testEquals() {
		
	}
	
	@Test
	public void testConstructor() {
		
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
		assertEquals("id: 12345 Name: toothbrush Cost: 15 Description: Teeth scrubber", i.toString());
	}

}
