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

import com.qa.ims.controller.ItemController;
import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;
@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
	@Mock
	private Utils utils;

	@Mock
	private OrderDAO dao;

	@InjectMocks
	private OrderController controller;
	
	@Test
	public void testCreate() { 
		final	long item_id=1;
		final long customer_id=1;
		final Order order = new Order(customer_id,item_id);
		Mockito.when(utils.getLong())
			.thenReturn(customer_id)
			.thenReturn(item_id);
		Mockito.when(dao.create(order)).thenReturn(order);
		
		assertEquals(order,controller.create());
		Mockito.verify(utils,Mockito.times(2)).getLong();
		Mockito.verify(dao,Mockito.times(1)).create(order);
	}
	@Test
	public void testUpdate() {
		Order order = new Order (1,1,0);
		Mockito.when(this.utils.getLong())
			.thenReturn(order.getOrder_id())
			.thenReturn(order.getItem_id())
			.thenReturn(order.getCustomer_id());
		Mockito.when(this.dao.update(order)).thenReturn(order);
		
		assertEquals(order, this.controller.update());	
		Mockito.verify(utils, Mockito.times(3)).getLong();
		Mockito.verify(dao, Mockito.times(1)).update(order);
	}

	
	
	@Test
	public void testReadAll() {
		List <Order> orders = new ArrayList<>();
		orders.add(new Order (1L,1,1,1,1));
		
		Mockito.when(dao.readAll()).thenReturn(orders);
		
		assertEquals(orders, controller.readAll());
		
		Mockito.verify(dao,Mockito.times(1)).readAll();
		
	}
	@Test
	public void testDeleteOrder() {
		final long ID = 1L;
		Mockito.when(this.utils.getString()).thenReturn("order");
		Mockito.when(this.utils.getLong()).thenReturn(ID);
		Mockito.when(this.dao.delete(ID)).thenReturn(1);
		
		
		assertEquals(1L,this.controller.delete());
		
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao,Mockito.times(1)).delete(ID);
	}
	@Test
	public void testDeleteItem() {
		final long ID = 1L;
		final long item_id =1L;
		Mockito.when(this.utils.getString()).thenReturn("item");
		Mockito.when(this.utils.getLong())
		.thenReturn(ID)
		.thenReturn(item_id);
		Mockito.when(this.dao.deleteItem(ID, item_id)).thenReturn(1);
		
		assertEquals(1L,this.controller.delete());
		Mockito.verify(utils,Mockito.times(2)).getLong();
		Mockito.verify(dao,Mockito.times(1)).deleteItem(ID,item_id);
	}
}
