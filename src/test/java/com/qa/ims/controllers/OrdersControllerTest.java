package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.OrdersController;
import com.qa.ims.persistence.dao.OrdersDAO;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrdersControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private OrdersDAO dao;

	@InjectMocks
	private OrdersController controller;

	@Test
	public void testCreate() {
		final Long customerID = 1L, productID = 1L;
		final Double orderQty = 1.0, totalPrice = 13.00;
		final Boolean orderStatus = false;
		final Orders created = new Orders(customerID, productID, orderQty, totalPrice, orderStatus);

		Mockito.when(utils.getLong()).thenReturn(customerID, productID);
		Mockito.when(utils.getDouble()).thenReturn(orderQty, totalPrice);
		Mockito.when(utils.getBoolean()).thenReturn(orderStatus);
		Mockito.when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(utils, Mockito.times(2)).getDouble();
		Mockito.verify(utils, Mockito.times(1)).getBoolean();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}

	@Test
	public void testReadAll() {
		List<Orders> order = new ArrayList<>();
		order.add(new Orders(1L, 1L, 1L, 1.0, 13.00, false));

		Mockito.when(dao.readAll()).thenReturn(order);

		assertEquals(order, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

	@Test
	public void testUpdate() {
		Orders updated = new Orders(1L, 1L, 1L, 1.0, 13.00, true);

		Mockito.when(this.utils.getLong()).thenReturn(1L);
		Mockito.when(this.utils.getLong()).thenReturn(updated.getCustomerID(), updated.getProductID());
		Mockito.when(this.utils.getDouble()).thenReturn(updated.getOrderQty(), updated.getTotalPrice());
		Mockito.when(this.utils.getBoolean()).thenReturn(updated.getOrderStatus());
		Mockito.when(this.dao.update(updated)).thenReturn(updated);

		assertEquals(updated, this.controller.update());

		Mockito.verify(this.utils, Mockito.times(3)).getLong();
		Mockito.verify(this.utils, Mockito.times(2)).getDouble();
		Mockito.verify(this.utils, Mockito.times(1)).getBoolean();
		Mockito.verify(this.dao, Mockito.times(1)).update(updated);
	}

	@Test
	public void testDelete() {
		final long orderID = 1L;

		Mockito.when(utils.getLong()).thenReturn(orderID);
		Mockito.when(dao.delete(orderID)).thenReturn(1);

		assertEquals(1L, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(orderID);
	}

}