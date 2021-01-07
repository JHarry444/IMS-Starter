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

import com.qa.ims.controller.OrderlineController;
import com.qa.ims.persistence.dao.OrderlineDAOTest;
import com.qa.ims.persistence.domain.Orderline;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrderlineControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private OrderlineDAOTest dao;

	@InjectMocks
	private OrderlineController controller;

	@Test
	public void testCreate() {
		final Long orderID = 1L, productID = 1L;
		final Orderline created = new Orderline(orderID, productID);

		Mockito.when(utils.getLong()).thenReturn(orderID, productID);
		Mockito.when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}

	@Test
	public void testReadAll() {
		List<Orderline> orderline = new ArrayList<>();
		orderline.add(new Orderline(1L, 1L, 1L));

		Mockito.when(dao.readAll()).thenReturn(orderline);

		assertEquals(orderline, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

	@Test
	public void testUpdate() {
		Orderline updated = new Orderline(1L, 2L);

		Mockito.when(this.utils.getLong()).thenReturn(1L);
		Mockito.when(this.utils.getLong()).thenReturn(updated.getOrderID(), updated.getProductID());
		Mockito.when(this.dao.update(updated)).thenReturn(updated);

		assertEquals(updated, this.controller.update());

		Mockito.verify(this.utils, Mockito.times(1)).getLong();
		Mockito.verify(this.dao, Mockito.times(2)).update(updated);
	}

	@Test
	public void testDelete() {
		final long orderlineID = 1L;

		Mockito.when(utils.getLong()).thenReturn(orderlineID);
		Mockito.when(dao.delete(orderlineID)).thenReturn(1);

		assertEquals(1L, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(orderlineID);
	}

}