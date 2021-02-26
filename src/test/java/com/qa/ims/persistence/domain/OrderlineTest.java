package com.qa.ims.persistence.domain;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class OrderlineTest {
	
	private Orderline orderlineTest;
	
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Orderline.class).verify();
	}
	@Test
	public void orderlineConstructor() {
		this.orderlineTest = new Orderline (1L, 1L, 1L);
	}
	@Test
	public void orderlineConstructor2() {
		this.orderlineTest = new Orderline (1L, 1L, 1L, 1L);
	}
}
