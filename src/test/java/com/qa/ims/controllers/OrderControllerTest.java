package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.CustomerController;
import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
		
	@Mock
	private Utils utils;

	@Mock
	private CustomerDAO customerDAO;
	
	@Mock
	private OrderDAO orderDAO;
	
	@Mock
	private ItemDAO itemDAO;

	@InjectMocks
	private OrderController orderController;

	@Test
	public void testCreate() {
		List<Customer> customers = new ArrayList<>();
		List<Item> items = new ArrayList<>();
		long customerID = 3L;
		long itemID = 2L;
		Item wheelieBin = new Item(itemID, "wheelie bin", 3.99, 84528L);
		Order expected = new Order(3l,3l,items);
		expected.getItems().add(wheelieBin);
		expected.getItems().add(wheelieBin);
		
		customers.add(new Customer(customerID, "Piers", "Barber"));
		items.add(wheelieBin);
		
		when(customerDAO.readAll()).thenReturn(customers);
		when(utils.getLong()).thenReturn(customerID,itemID, itemID);
		when(orderDAO.create(any(Order.class))).thenReturn(new Order(3l,3l,new ArrayList<>()));
		when(itemDAO.readAll()).thenReturn(items);
		// No need to mock orderDAO.createLine() as return is void anyway.
		when(utils.getString()).thenReturn("yes", "no");
		when(orderDAO.readLatest()).thenReturn(expected);
		
		assertEquals(expected, orderController.create());
		
		verify(customerDAO, times(1)).readAll();
		verify(utils, times(3)).getLong();
		verify(utils, times(2)).getString();
		verify(orderDAO, times(1)).create(any(Order.class));
		verify(itemDAO, times(2)).readAll();
		verify(orderDAO, times(1)).readLatest();
	}

	@Test
	public void testReadAll() {
		List<Order> expected = new ArrayList<>();
		List<Item> items = new ArrayList<>();
		Order order1 = new Order(3l,3l,items);
		Order order2 = new Order(3l,3l,items);
		Item wheelieBin = new Item(3L, "wheelie bin", 3.99, 84528L);
		Item nikeAirs = new Item(4L, "nike airs", 40.99, 74L);
		order1.getItems().add(wheelieBin);
		order2.getItems().add(nikeAirs);
		expected.add(order1);
		expected.add(order2);
		for (Order order : expected) {
			for(Item item:order.getItems()) {
				item.setQuantity(1L);
			}
		}
		
		when(orderDAO.readAll()).thenReturn(expected);
		
		assertEquals(expected, orderController.readAll());
		
		verify(orderDAO, times(1)).readAll();
	}

	@Test
	public void testUpdate() {
		List<Customer> customers = new ArrayList<>();
		List<Item> items = new ArrayList<>();
		long customerID = 3L;
		long itemID = 2L;
		long orderID = 3l;
		
		Item wheelieBin = new Item(itemID, "wheelie bin", 3.99, 84528L);
		Order current = new Order(orderID,3l,items);
		
		current.getItems().add(wheelieBin);
		current.getItems().add(wheelieBin);
		
		customers.add(new Customer(customerID, "Piers", "Barber"));
		items.add(wheelieBin);
		
		when(utils.getLong()).thenReturn(orderID, orderID, itemID, itemID);
		when(orderDAO.readOrder(orderID)).thenReturn(null, current, current, current, current, current, current);
		when(itemDAO.readAll()).thenReturn(items);
		// No need to mock orderDAO.deleteLine() or createLine() as return is void anyway.
		when(utils.getString()).thenReturn("delete", "add", "yes?", "exit");
		
		assertEquals(current, orderController.update());
		
		verify(utils, times(4)).getLong();
		verify(utils, times(4)).getString();
		verify(orderDAO, times(7)).readOrder(anyLong());
		verify(itemDAO, times(1)).readAll();
	}

	@Test
	public void testDelete() {
		long orderID = 3l;
		long itemID = 2L;
		
		Item wheelieBin = new Item(itemID, "wheelie bin", 3.99, 84528L);
		List<Item> items = new ArrayList<>();
		List<Order> expected = new ArrayList<>();
		
		Order order = new Order(orderID,3l,items);
		order.getItems().add(wheelieBin);
		expected.add(order);
		
		when(orderDAO.readAll()).thenReturn(expected);
		when(utils.getLong()).thenReturn(orderID);
		when(orderDAO.delete(orderID)).thenReturn(1);
		
		assertEquals(1, orderController.delete());
		
		verify(orderDAO, times(1)).readAll();
		verify(utils, times(1)).getLong();
		verify(orderDAO, times(1)).delete(anyLong());
	}
}
