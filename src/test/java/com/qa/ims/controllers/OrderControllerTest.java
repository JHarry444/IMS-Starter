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
		Mockito.when(orderDAO.readAllItems(false)).thenReturn(orders);
		Mockito.when(utils.getString()).thenReturn(choice);

		// ACTIONS
		final List<Order> result = orderController.readAll();

		// ASSERTIONS
		assertEquals(new ArrayList<>(), result);
		// assertEquals(orders, result);
		// Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(orderDAO, Mockito.times(1)).readAllItems(false);
	}

	@Test
	public void readAllByIdTest() {
		// RESOURCES
		final String choice = "ID";
		
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(1L, 1L));
		
		// RULES
		Mockito.when(orderDAO.readAllItems(false)).thenReturn(orders);
	//	Mockito.when(orderDAO.readAllItems(true)).thenReturn(orders);
		Mockito.when(utils.getString()).thenReturn(choice);
		
		// ACTIONS
		final List<Order> result = orderController.readAll();
		
		// ASSERTIONS
		assertEquals(orders, result);
		Mockito.verify(orderDAO, Mockito.times(1)).readAllItems(false);
	}

	@Test
	public void deleteByOrderTest() {
		// RESOURCES
		final long id = 1L;
		final String choice = "Order";

		// RULES
		Mockito.when(utils.getLong()).thenReturn(id);
		Mockito.when(orderDAO.delete(id)).thenReturn(1);
		Mockito.when(utils.getString()).thenReturn(choice);

		// ACTIONS
		final int result = this.orderController.delete();

		// ASSERTIONS
		assertEquals(1L, result);
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(orderDAO, Mockito.times(1)).delete(id);
	}

	@Test
	public void deleteByItemTest() {
		// RESOURCES
		final long id = 1L;
		final String choice = "Item";
		final int expected = 0;

		// RULES
		Mockito.when(utils.getLong()).thenReturn(id);
	//	Mockito.when(orderDAO.delete(id)).thenReturn(1);
		Mockito.when(utils.getString()).thenReturn(choice);
		Mockito.when(orderController.deleteItem(id)).thenReturn(expected);

		// ACTIONS
		final int result = this.orderController.delete();

		// ASSERTIONS
		assertEquals(expected , result);
		Mockito.verify(utils, Mockito.times(3)).getLong();
		Mockito.verify(utils, Mockito.times(1)).getString();
		// Mockito.verify(orderController, Mockito.times(1)).deleteItem(Id);

	}

	@Test
	public void deleteByInvalid() {
		// RESOURCES
		final long id = 1L;
		final String choice = "";
		final int expected = 0;

		// RULES
		Mockito.when(utils.getLong()).thenReturn(id);
	//	Mockito.when(orderDAO.delete(id)).thenReturn(1);
		Mockito.when(utils.getString()).thenReturn(choice);
		Mockito.when(orderController.deleteItem(id)).thenReturn(expected);

		// ACTIONS
		final int result = this.orderController.delete();

		// ASSERTIONS
		assertEquals(expected , result);
		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(utils, Mockito.times(1)).getString();
	}

	@Test
	public void calculateCostTest() {
		//RESOURCES
		final Double expected = 0.0;
		final List<Order> orders = new ArrayList();
		orders.add(new Order(1L,"", "", 1L, 1L, "", 1.0, "", 1.0));
		
		// RULES
		Mockito.when(orderController.calculateCost(orders)).thenReturn(expected);
		Mockito.when(orderDAO.calculateCost(orders)).thenReturn(expected);
		// ACTIONS
		final Double result = orderDAO.calculateCost(orders);
		
		// ASSERTIONS
		assertEquals(expected, result);
		Mockito.verify(orderDAO, Mockito.times(1)).calculateCost(orders);
	}

	@Test
	public void deleteNullOrdersTest() {
		final int expected = 0;
		assertEquals(expected, orderController.deleteNullOrders());
	}
	
	@Test
	public void readSpecificTest() {
		// RESOURCES
		final long id = 1L;
		final List<Order> expected = new ArrayList<Order>();
		expected.add(new Order(id,"o", "o", 1L, 1L, "o", 1.0, "o", 1.0));
		
		// RULES
		Mockito.when(utils.getLong()).thenReturn(id);
		
		
		// ACTIONS 
		final List<Order> result = orderController.readSpecific();
		
		// ASSERTIONS 
		
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(orderDAO, Mockito.times(1)).readSpecific(id);
		assertEquals(new ArrayList<>(), result);
		//assertEquals(expected, result);
	}

}
