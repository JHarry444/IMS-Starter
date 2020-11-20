package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class OrderTest {
	
	private List<Item> items = new ArrayList<>();
	private Order testOrder1 = new Order(1l, 1l, items);
	private Order testOrder2 = new Order(1l, 1l, items);
	private Order testOrder3 = new Order(1l, 1l, items);
	private Order nullOrder1 = new Order(null, null, null);
	private Order nullOrder2 = new Order(1l, null, null);
	private Order nullOrder3 = new Order(1l, 1l, null);
	private Item item = new Item(null, null, null);
	
	@Test
	public void testSetters() {
		testOrder1.setCustomer_id(2l);
		testOrder1.setId(2l);
		testOrder1.setItem(new ArrayList<>());
		
		assertFalse(testOrder1.equals(testOrder2));
	}
	@Test
	public void testGetters() {
		assertTrue(testOrder1.getId().equals(1l));
		assertTrue(testOrder1.getCustomer_id().equals(1l));
		assertTrue(testOrder1.getItems().equals(new ArrayList<>()));
	}
	@Test
	public void testEquals() {
		assertTrue(testOrder1.equals(testOrder2));
		assertFalse(nullOrder1.equals(testOrder1));
		assertFalse(testOrder1.equals(null));
		assertFalse(testOrder1.equals(item));
		assertFalse(nullOrder1.equals(nullOrder2));
		assertFalse(nullOrder1.equals(nullOrder3));
		assertFalse(nullOrder2.equals(testOrder1));
		assertFalse(nullOrder2.equals(nullOrder3));
		assertFalse(nullOrder3.equals(testOrder1));
		assertFalse(nullOrder3.equals(nullOrder2));
	}

}
