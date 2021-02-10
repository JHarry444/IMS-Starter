package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private OrderDAO orderDAO;

	@InjectMocks
	private OrderController orderController;

	@Test
	public void testCreateOrder() {
		// RESOURCES
		final Long customerId = 1L;
		final Order created = new Order(customerId);
		final String choice = "N";

		// RULES
		Mockito.when(utils.getLong()).thenReturn(customerId);
		Mockito.when(orderDAO.createUpdateOrder(Mockito.any(Order.class), Mockito.anyBoolean())).thenReturn(created);
		Mockito.when(utils.getString()).thenReturn(choice);

		// ACTIONS
		final Order result = orderController.create();

		// ASSERTIONS
		assertEquals(created, result);
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(orderDAO, Mockito.times(1)).createUpdateOrder(Mockito.any(Order.class), Mockito.anyBoolean());

	}

	@Test
	public void createOrderItemTest() {
		// RESOURCES
		final Long itemId = 1L;
		final Long orderId = 1L;
		final Long quantity = 5L;
		final Order created = new Order(itemId, orderId, quantity);

		// RULES
		Mockito.when(utils.getLong()).thenReturn(itemId);
		Mockito.when(utils.getLong()).thenReturn(quantity);
		Mockito.when(orderDAO.createUpdateOrderItem(Mockito.any(Order.class), Mockito.anyBoolean()))
				.thenReturn(created);

		// ACTIONS
		final Order result = orderController.createOrderItem(orderId);

		// ASSERTIONS
		assertEquals(created, result);
		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(orderDAO, Mockito.times(1)).createUpdateOrderItem(Mockito.any(Order.class),
				Mockito.anyBoolean());

	}

	@Test
	public void updateTest() {
		// RESOURCES
		final Long orderId = 1L;
		final Long itemId = 1L;
		final Long quantity = 1L;
		final Order updated = new Order(itemId, orderId, quantity);

		// RULES
		Mockito.when(utils.getLong()).thenReturn(orderId);
		Mockito.when(utils.getLong()).thenReturn(itemId);
		Mockito.when(utils.getLong()).thenReturn(quantity);
		Mockito.when(orderDAO.createUpdateOrder(Mockito.any(Order.class), Mockito.anyBoolean())).thenReturn(updated);

		// ACTIONS
		final Order result = orderController.update();

		// ASSERTIONS
		assertEquals(updated, result);
		Mockito.verify(utils, Mockito.times(3)).getLong();
		Mockito.verify(orderDAO, Mockito.times(1)).createUpdateOrder(Mockito.any(Order.class), Mockito.anyBoolean());

	}

	@Test
	public void readAllTest() {
		// RESOURCES
		
		final String choice = "ALL"; 
	
		
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(1L, 1L));

		// RULES 
		Mockito.when(utils.getString()).thenReturn(choice);
		Mockito.when(orderDAO.readAll()).thenReturn(orders);
		
		// ACTIONS
		final List <Order> result = orderDAO.readAll();
		
		// ASSERTIONS
		assertEquals(orders, result);
		Mockito.verify(orderDAO, Mockito.times(1)).readAll(); 
	} 
	
	@Test
	public void deleteTest() {
		// RESOURCES
		final long Id = 1L;
		
		// RULES
		Mockito.when(utils.getLong()).thenReturn(Id);
		Mockito.when(orderDAO.delete(Id)).thenReturn(1);
		
		// ACTIONS
		final int result = this.orderController.delete();
		
		// ASSERTIONS
		assertEquals(1L, result);
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(orderDAO, Mockito.times(1)).delete(Id);
	}

	@Test
	public void deleteItemTest() {

	}

	@Test
	public void calculateCostTest() {

	}

	@Test
	public void deleteNullOrdersTest() {

	}

}
