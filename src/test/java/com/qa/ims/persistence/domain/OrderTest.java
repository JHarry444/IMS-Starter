package com.qa.ims.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class OrderTest {
    
    @Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Order.class).verify();
	}

	@Test
	public void testOrder() {
		Order order = new Order(2L);
		assertNotNull(order);
		assertEquals(2L, order.getCustID());
	}

	@Test
	public void testOrderWithID() {
		Order order = new Order(1L, 2L);
		assertNotNull(order);
		assertEquals(1L, order.getId());
		assertEquals(2L, order.getCustID());
	}

	@Test
	public void testGetId() {
		Order order = new Order(1L, 2L);
		assertEquals(1L, order.getId());
	}

	@Test
	public void testSetId() {
		Order order = new Order(1L, 2L);
		order.setId(2L);
		assertEquals(2L, order.getId());
	}

	@Test
	public void testGetFirstName() {
		Order order = new Order(1L, 2L);
		assertEquals(2L, order.getCustID());
	}

	@Test
	public void testSetFirstName() {
		Order order = new Order(1L, 2L);
		order.setCustID(5L);
		assertEquals(5L, order.getCustID());
	}

	@Test
	public void testGetString() {
		Order order = new Order(1L, 2L);
		assertEquals("id:" + order.getId() + " customer ID:" + order.getCustID(), order.toString());
	}

	@Test
	public void testGetFormattedString() {
		Order order = new Order(1L, 2L);
		assertEquals("ID: " + order.getId() + ", Customer ID: " + order.getCustID(), order.formattedString());
	}

}
