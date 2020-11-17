package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.DBUtils;

public class OrderTest {
	
	private final Order order = new Order(1l,1l,"JIM","STUFF",34.04D);
	
	@Test
	public void TestGetCustomerID() {
		final Long CustomerID = 1L;
		assertEquals(CustomerID,order.getCustomerID());
	}
	@Test
	public void TestGetCustomer() {
		final String customer = "JIM";
		assertEquals(customer,order.getCustomer());
	}
	
	
}
