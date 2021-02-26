package com.qa.ims.persistence.domain;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;



public class CustomerTest {
	
	private Customer customerTest;

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Customer.class).verify();
	}
	@Test
	public void CustomerConstructor() {
		this.customerTest = new Customer (1L, "Abraham", "Lincoln");
	}
	@Test
	public void CustomerConstructor2() {
		this.customerTest = new Customer ("Abraham", "Lincoln");
	}

}
