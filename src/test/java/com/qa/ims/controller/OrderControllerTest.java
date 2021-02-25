package com.qa.ims.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.qa.ims.controller.*;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {
    
    
	@Mock
	private Utils utils;

	@Mock
	private OrderDAO dao;

	@InjectMocks
	private OrderController controller;

	@Test
	public void testCreate() {
		final String NAME = "apple";
        final Double PRICE = 1.5D;
		final Order created = new Order(NAME, PRICE);

		Mockito.when(utils.getString()).thenReturn(NAME);
        Mockito.when(utils.getDouble()).thenReturn(PRICE);
		Mockito.when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(1)).getString();
        Mockito.verify(utils, Mockito.times(1)).getDouble();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}

	@Test
	public void testReadAll() {
		List<Order> Orders = new ArrayList<>();
		Orders.add(new Order(1L, "apple", 1.5D));

		Mockito.when(dao.readAll()).thenReturn(Orders);

		assertEquals(Orders, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

	@Test
	public void testUpdate() {
		Order updated = new Order(1L, "banana", 2.5D);

		Mockito.when(this.utils.getLong()).thenReturn(1L);
		Mockito.when(this.utils.getString()).thenReturn(updated.getName());
        Mockito.when(this.utils.getDouble()).thenReturn(updated.getPrice());
		Mockito.when(this.dao.update(updated)).thenReturn(updated);

		assertEquals(updated, this.controller.update());

		Mockito.verify(this.utils, Mockito.times(1)).getLong();
		Mockito.verify(this.utils, Mockito.times(1)).getString();
        Mockito.verify(this.utils, Mockito.times(1)).getDouble();
		Mockito.verify(this.dao, Mockito.times(1)).update(updated);
	}

	@Test
	public void testDelete() {
		final long ID = 1L;

		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(utils.getString()).thenReturn("Y");
		Mockito.when(dao.delete(ID)).thenReturn(1);

		assertEquals(1, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
        Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(dao, Mockito.times(1)).delete(ID);
	}
}
