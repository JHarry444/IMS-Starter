package com.qa.ims.persistence.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;



public class OrderTest {

//	CONSTRUCTORS
	@Test
	public void constructorTest1() {
		LocalDate ordOn = LocalDate.of(2021, 5, 31);
		Order order = new Order((long) 2, (long) 43, 65.83, ordOn);
		assertEquals(2, order.getId());
		assertEquals(43, order.getCustomerId());
		assertEquals("$65.83", order.getTotalString());
		assertEquals(65.83, order.getTotalDouble());
		assertEquals("31-05-2021", order.getOrderedOn());
	}
	
	@Test
	public void constructorTest2() {
		LocalDate ordOn = LocalDate.of(2021, 5, 31);
		Order order = new Order((long) 234, (long) 21, "188.22", ordOn);
		assertEquals(234, order.getId());
		assertEquals(21, order.getCustomerId());
		assertEquals("$188.22", order.getTotalString());
		assertEquals(188.22, order.getTotalDouble());
		assertEquals("31-05-2021", order.getOrderedOn());
	}
	
//	SETTERS
	@Test
	public void settersTest() {
		Order order = new Order((long) 41, (long) 3, "53.35", LocalDate.of(2021, 2, 19));
		assertEquals(41, order.getId());
		order.setId((long) 9);
		assertEquals(9, order.getId());
		assertEquals(3, order.getCustomerId());
		order.setCustomerId(((long) 79));
		assertEquals(79, order.getCustomerId());
		assertEquals("$53.35", order.getTotalString());
		order.setTotal(44.42);
		assertEquals(44.42, order.getTotalDouble());
		assertEquals("19-02-2021", order.getOrderedOn());
		order.setOrderedOn(LocalDate.of(2021, 2, 18));
		assertEquals("18-02-2021", order.getOrderedOn());
	}
	
//	OVERRIDES
	@Test
	public void toStringTest() {
		Order order = new Order((long) 87329, (long) 349, "85.03", LocalDate.of(2021, 4, 25));
		assertEquals("id: 87329, customerId: 349, total: 85.03, orderedOn: 25-04-2021", order.toString());
	}
	
	@Test
	public void equalsTest() {
		Order order = new Order((long) 1, (long) 159, "1097.33", LocalDate.of(2020, 12, 2));
		Order order2 = new Order((long) 432, (long) 414, "101.42", LocalDate.of(2021, 4, 4));
		assertFalse(order.equals(order2));
		Order order3 = order2;
		assertTrue(order2.equals(order3));
		Order order4 = new Order((long) 20432, (long) 414, "20.99", LocalDate.of(2021, 12, 10));
		assertFalse(order3.equals(order4));
		Order order5 = new Order((long) 843, (long) 414, "20.99", LocalDate.of(2021, 12, 11));
		assertFalse(order4.equals(order5));
		Order order6 = new Order((long) 843, (long) 414, "20.99", LocalDate.of(2021, 12, 11));
		assertTrue(order5.equals(order6));
	}
}
