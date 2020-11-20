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
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrdersControllerTest {
	@Mock
	private Utils utils;
	
	@Mock
	private OrdersDAO DAO;
	
	@InjectMocks
	private OrdersController controller;
	
	@Test
	public void TestreadAll() {
		List<Orders> order = new ArrayList<>();
		order.add(new Orders(1L,1L));
		
		Mockito.when(DAO.readAll()).thenReturn(order);
		
		assertEquals(order, controller.readAll());
		
		Mockito.verify(DAO, Mockito.times(1)).readAll();
	}
	
//	@Test
//	public void testCreate() {
//		List<Item> items = new ArrayList<>();
//		items.add(new Item(2L, "key", null));
//		final Long order_id = 2L; // Customer id 
//		final int item_id = 2; // Item id from `items` 
//		final int quantity = 2;
//		final Orders created = new Orders(order_id, item_id, quantity);
//		
//		Mockito.when(utils.getLong()).thenReturn(order_id);
//		Mockito.when(utils.getInt()).thenReturn(item_id);
//		Mockito.when(utils.getInt()).thenReturn(quantity);
//		Mockito.when(DAO.create(Mockito.any(Orders.class))).thenReturn(created);
//		
//		assertEquals(created, controller.create());
//		
////		Mockito.verify(utils, Mockito.times(1)).getLong();
////		Mockito.verify(utils, Mockito.times(1)).getInt();
////		Mockito.verify(utils, Mockito.times(1)).getInt();
//		
//	}
//	
	@Test
	public void testDelete() {
		final Long order_id = 1L;
		
		Mockito.when(utils.getLong()).thenReturn(order_id);
		Mockito.when(DAO.delete(order_id)).thenReturn(1);
		
		assertEquals(1L, this.controller.delete());
		
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(DAO, Mockito.times(1)).delete(order_id);
	}

}
