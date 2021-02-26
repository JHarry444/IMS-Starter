package com.qa.ims.persistence.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import nl.jqno.equalsverifier.EqualsVerifier;

public class ItemTest {
	
	private Item testItem;

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Item.class).verify();
	}
	
	@Test
	public void ItemConstructor() {
		this.testItem = new Item("Apple", 1.50D);
		
	}
	
	@Test
	public void ItemConstructor2() {
		this.testItem = new Item(1L, "Banana", 1.00D);
	}
	
	
}

