package com.qa.ims.persistence.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;



public class OrderTest {

//	CONSTRUCTORS
	@Test
	public void constructorTest1() throws ParseException {
		LocalDate date1 = LocalDate.of(2021, 4, 25);
		Order order = new Order((long) 2, (long) 43, 65.83, date1);
		assertEquals(2, order.getId());
		assertEquals(43, order.getCustomerId());
		assertEquals("$65.83", order.getTotalString());
		assertEquals(65.83, order.getTotalDouble());
		System.out.println(order.getOrderedOn());
		assertEquals("2021-04-25", order.getOrderedOn());
	}
	
	@Test
	public void constructorTest2() {
		LocalDate date = LocalDate.of(2021, 1, 12);
		Order order = new Order((long) 234, (long) 21, "188.22", date);
		assertEquals(234, order.getId());
		assertEquals(21, order.getCustomerId());
		assertEquals("$188.22", order.getTotalString());
		assertEquals(188.22, order.getTotalDouble());
		assertEquals("2021-01-12", order.getOrderedOn());
	}
	
	@Test
	public void constructorTest3() {
		Order o = new Order((long) 22, 199.99, LocalDate.of(2020, 12, 22));
		assertNull(o.getId());
		Order o2 = new Order((long) 23, "20.88", LocalDate.of(2020, 1, 2));
		assertNull(o.getId());
	}
	
//	SETTERS
	@Test
	public void settersTest() {
		LocalDate date = LocalDate.of(2020, 12, 2);
		Order order = new Order((long) 41, (long) 3, "53.35", date);
		assertEquals(41, order.getId());
		order.setId((long) 9);
		assertEquals(9, order.getId());
		assertEquals(3, order.getCustomerId());
		order.setCustomerId(((long) 79));
		assertEquals(79, order.getCustomerId());
		assertEquals("$53.35", order.getTotalString());
		order.setTotal(44.42);
		assertEquals(44.42, order.getTotalDouble());
		assertEquals("2020-12-02", order.getOrderedOn());
		LocalDate date2 = LocalDate.of(2020, 5, 5);
		order.setOrderedOn(date2);
		assertEquals("2020-05-05", order.getOrderedOn());
	}
	
//	OVERRIDES
	@Test
	public void toStringTest() {
		LocalDate date = LocalDate.of(2021, 5, 15);
		Order order = new Order((long) 87329, (long) 349, "85.03", date);
		assertEquals("id: 87329, customerId: 349, total: 85.03, orderedOn: 2021-05-15", order.toString());
	}
	
	@Test
	public void equalsTest() {
		LocalDate date = LocalDate.of(2020, 8, 14);
		Order order = new Order((long) 1, (long) 159, "1097.33", date);
		LocalDate date2 = LocalDate.of(2020, 11, 14);
		Order order2 = new Order((long) 432, (long) 414, "101.42", date2);
		assertFalse(order.equals(order2));
		Order order3 = order2;
		assertTrue(order2.equals(order3));
		LocalDate ordOn3 = LocalDate.of(2020, 6, 6);
		Order order4 = new Order((long) 20432, (long) 414, "20.99", ordOn3);
		assertFalse(order3.equals(order4));
		LocalDate ordOn4 = LocalDate.of(2021, 1, 11);
		Order order5 = new Order((long) 843, (long) 414, "20.99", ordOn4);
		assertFalse(order4.equals(order5));
		Order order6 = new Order((long) 843, (long) 414, "20.99", ordOn4);
		assertTrue(order5.equals(order6));
		Boolean res1 = order5.equals(null);
		Item i = new Item("Spaulding", "Basketball", 19.99);
		Boolean res2 = order5.equals(i);
	}
}
