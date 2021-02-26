package com.qa.ims.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class OrderTest {

	private static Order testOrder;
	
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Order.class).verify();
	}
	
	@Test
	public void firstOrderConstructorTest() {
		testOrder = new Order(1, 2l, 1);
		assertNotNull(testOrder);
		assertTrue(testOrder instanceof Order);
	}
	
	@Test
	public void secondOrderConstructorTest() {
		testOrder = new Order(1l, 2l, 3);
		assertNotNull(testOrder);
	}
	
	@Test
	public void thirdOrderConstructorTest() {
		testOrder = new Order(1l, 2);
		assertNotNull(testOrder);
	}
	
	@Test
	public void forthOrderConstructorTest() {
		testOrder = new Order(1l, 2, 5.50f);
		assertNotNull(testOrder);
	}
	
	@Test
	public void fifthOrderConstructorTest() {
		testOrder = new Order(1l, 2, 2l, 3, 6.50f);
		assertNotNull(testOrder);
	}
	
	@Test
	public void getOrder_idTest() {
		Order ord = new Order(1l, 2l, 3);
		assertEquals(1l, ord.getOrder_id());
	}
	
	@Test
	public void setOrder_idTest() {
		Order ord = new Order(2, 2l, 3);
		ord.setOrder_id(2l);
		assertEquals(2l, ord.getOrder_id());
	}
	
	@Test
	public void getCust_idTest() {
		Order ord = new Order(2, 2l, 3);
		assertEquals(2, ord.getCust_id());
	}
	
	@Test
	public void getItem_idTest() {
		Order ord = new Order(2, 2l, 3);
		assertEquals(2, ord.getItem_id());
	}
	
	@Test
	public void getQuantityTest() {
		Order ord = new Order(2, 2l, 3);
		assertEquals(3, ord.getQuantity());
	}
	
	@Test
	public void getTotal_costTest() {
		Order ord = new Order(2l, 2, 3.50f);
		assertEquals(3.50f, ord.getTotal_cost());
	}
	
	@Test
	public void toStringTest() {
		Order ord = new Order(1l, 1, 1l, 3, 5.50f);
		assertNotNull("order_id:1l,cust_id:1,item_id:1l,quantity:3,total_cost:5.50f", ord.toString());
	}
	
	
}
