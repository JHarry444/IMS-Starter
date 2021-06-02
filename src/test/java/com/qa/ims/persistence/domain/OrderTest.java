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
		Date date1 = new Date();
		date1.setYear(2021);
		date1.setMonth(4);
		date1.setDate(25);
		Order order = new Order((long) 2, (long) 43, 65.83, date1);
		assertEquals(2, order.getId());
		assertEquals(43, order.getCustomerId());
		assertEquals("$65.83", order.getTotalString());
		assertEquals(65.83, order.getTotalDouble());
		assertEquals("2021-04-25", order.getOrderedOnString());
	}
	
	@Test
	public void constructorTest2() {
		Date date1 = new Date();
		date1.setYear(2021);
		date1.setMonth(1);
		date1.setDate(1);
		Order order = new Order((long) 234, (long) 21, "188.22", date1);
		assertEquals(234, order.getId());
		assertEquals(21, order.getCustomerId());
		assertEquals("$188.22", order.getTotalString());
		assertEquals(188.22, order.getTotalDouble());
		assertEquals("2021-01-01", order.getOrderedOnString());
	}
	
//	SETTERS
	@Test
	public void settersTest() {
		Date date1 = new Date();
		date1.setYear(2021);
		date1.setMonth(4);
		date1.setDate(25);
		Order order = new Order((long) 41, (long) 3, "53.35", date1);
		assertEquals(41, order.getId());
		order.setId((long) 9);
		assertEquals(9, order.getId());
		assertEquals(3, order.getCustomerId());
		order.setCustomerId(((long) 79));
		assertEquals(79, order.getCustomerId());
		assertEquals("$53.35", order.getTotalString());
		order.setTotal(44.42);
		assertEquals(44.42, order.getTotalDouble());
		assertEquals("2021-04-25", order.getOrderedOnString());
		Date date5 = new Date();
		date5.setYear(2021);
		date5.setMonth(1);
		date5.setDate(15);
		order.setOrderedOn(date5);
		System.out.println(order.getOrderedOnString());
		assertEquals("2021-01-15", order.getOrderedOnString());
	}
	
//	OVERRIDES
	@Test
	public void toStringTest() {
		Date date = new Date();
		date.setYear(2021);
		date.setMonth(3);
		date.setDate(2);
		Order order = new Order((long) 87329, (long) 349, "85.03", date);
		assertEquals("id: 87329, customerId: 349, total: 85.03, orderedOn: 2021-03-02", order.toString());
	}
	
	@Test
	public void equalsTest() {
		Date date = new Date();
		date.setYear(2021);
		date.setMonth(4);
		date.setDate(1);
		Order order = new Order((long) 1, (long) 159, "1097.33", date);
		Date date2 = new Date();
		date2.setYear(2020);
		date2.setMonth(11);
		date2.setDate(4);
		Order order2 = new Order((long) 432, (long) 414, "101.42", date2);
		assertFalse(order.equals(order2));
		Order order3 = order2;
		assertTrue(order2.equals(order3));
		Date date3 = new Date();
		date3.setYear(2020);
		date3.setMonth(5);
		date3.setDate(15);
		Order order4 = new Order((long) 20432, (long) 414, "20.99", date3);
		assertFalse(order3.equals(order4));
		Date date4 = new Date();
		date4.setYear(2020);
		date4.setMonth(7);
		date4.setDate(4);
		Order order5 = new Order((long) 843, (long) 414, "20.99", date4);
		assertFalse(order4.equals(order5));
		Order order6 = new Order((long) 843, (long) 414, "20.99", date4);
		assertTrue(order5.equals(order6));
	}
}
