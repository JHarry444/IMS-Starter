package com.qa.ims.persistence.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class OrderTest {

	Order o = new Order(1L, 1L);
	Order orderOne = new Order(1L, "Charlie", "Herriott", 1L, 1L, "Tooth Brush", 0.0, "Brusher", 0.0);
	

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Order.class).verify();
	}

	@Test
	public void testConstructor() {
		Order orderOne = new Order(1L, "Charlie", "Herriott", 1L, 1L, "Tooth Brush", 0.0, "Brusher", 0.0);
		Order orderTwo = new Order(2L, 2L, 2L);
		Order orderThree = new Order(3L);
	}

	@Test
	public void getFnameTest() {
		o.setFname("Terry");
		assertEquals("Terry", o.getFname());
	}

	@Test
	public void getSnameTest() {
		o.setSname("Mark");
		assertEquals("Mark", o.getSname());
	}

	@Test
	public void getItemIdTest() {
		o.setItemId(3L);
		long result = o.getItemId();
		assertEquals(3L, result);
	}

	@Test
	public void getQuantityTest() {
		o.setQuantity(3L);
		long result = o.getQuantity();
		assertEquals(3L, result);
	}

	@Test
	public void getItemNameTest() {
		o.setItemName("Book");
		assertEquals("Book", o.getItemName());
	}

	@Test
	public void getItemCostTest() {
		o.setItemCost(3.0);
		double result = o.getItemCost();
		assertEquals(3.0, result,1);
	}

	@Test
	public void getItemDescTest() {
		o.setItemDesc("Clicker");
		assertEquals("Clicker", o.getItemDesc());
	}

	@Test
	public void getOrderIdTest() {
		o.setOrderId(1L);
		long result = o.getOrderId();
		assertEquals(1L, result);
	}
	
	@Test
	public void getTotalCostTest() {
		o.setTotalCost(10.0);
		double result = o.getTotalCost();
		assertEquals(10.0, result,1);
		
	}
	
	@Test
	public void customerIdTest() {
		o.setCustomerId(1L);
		long result = o.getCustomerId();
		assertEquals(1L, result);
	}

	@Test
	public void toStringTest() {
		String expected = "Customer ID: 1 Order ID: 1";
		assertEquals(expected, o.toString());
	}
	
	@Test
	public void itemsToStringTest() {
		Order orderOne = new Order(1L, "Charlie", "Herriott", 1L, 1L, "Tooth Brush", 0.0, "Brusher", 0.0);
		String expected = "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------\nName: Charlie Herriott | Order ID: 1 | Item ID: 1 | Qty: 1 | Item: Tooth Brush | Item Cost: £0.0 | Description: Brusher | Total Cost: £0.0";
		assertEquals(expected, orderOne.itemsToString());
	}

}
