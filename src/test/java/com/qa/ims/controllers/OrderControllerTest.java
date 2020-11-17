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

import com.qa.ims.controller.OrderAction;
import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.dao.Order_ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
	@Mock
	private Utils utils;

	@Mock
	private OrderDAO DAO;
	
	@Mock
	private Order_ItemDAO OI_DAO;

	@InjectMocks
	private OrderController controller;
	
	@Test
	public void TestReadAll() {
		final List<Order> orders = new ArrayList<Order>();
		final Order read = new Order(1l,1l);
		orders.add(read);
		
		Mockito.when(DAO.readAll()).thenReturn(orders);
		
		assertEquals(orders,controller.readAll());
	}
	
	@Test
	public void TestDelete() {
		final Long ID = 1L;
	
		Mockito.when(utils.getLong()).thenReturn(ID);
		
		assertEquals(0,controller.delete());
		
		Mockito.verify(DAO,Mockito.times(1)).delete(ID);
	}
	@Test
	public void TestCreate() {
		final Long CustID = 2L;
		final Long ItemID = 1L;
		final Order order = new Order(CustID);
		
		Mockito.when(utils.getLong()).thenReturn(CustID,ItemID,ItemID);
		Mockito.when(DAO.create(order)).thenReturn(order);
		Mockito.when(utils.getString()).thenReturn("y","sd","n");
		Mockito.when(DAO.readLatest()).thenReturn(order);
		
		assertEquals(order,controller.create());
	}
	
	@Test
	public void TestUpdate() {
		final Long OrderID = 1L;
		final Long ItemID =1L;
		final Long CustomerID =1L;
		final Order order  = new Order(OrderID,ItemID);
		
		Mockito.when(utils.getLong()).thenReturn(OrderID,ItemID,ItemID,CustomerID);
		Mockito.when(DAO.readSingle(OrderID)).thenReturn(order);
		Mockito.when(utils.getString()).thenReturn("ADD","REMOVE","CUSTOMER","sdfg","RETURN");
		
		
		assertEquals(null,controller.update());
	}
	
}
