package com.qa.ims.persistence.domain;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class OrderTest {

	private Order orderTest;

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Order.class).verify();
	}
	
	@Test
	public void orderConstructor() {
		this.orderTest = new Order (1L, 1L, 1L);
	}
	@Test
	public void orderConstructor2() {
		this.orderTest = new Order (1L, 1L, 1L, 1L, 1L);
	}
	@Test
	public void orderConstructor3() {
		this.orderTest = new Order (1L, 1L, 1L, 1L);
	}
	@Test
	public void orderConstructor4() {
		this.orderTest = new Order (1L);
	}
}
