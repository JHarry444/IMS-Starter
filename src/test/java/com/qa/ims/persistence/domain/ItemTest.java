package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class ItemTest {
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Item.class).verify();
	} 
	
	@Test
	public void testitem() {
		Item item = new Item(2L,"Pizza",100);
		assertEquals(2L,item.getItem_id() );
	}
}
