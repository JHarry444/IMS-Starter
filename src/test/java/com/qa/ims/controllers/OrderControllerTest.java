package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private OrderDAO dao;

	@InjectMocks
	private OrderController controller;

	@Test
	public void createTest() {
		OrderController controller = new OrderController(dao, utils);
		Long customerId = 12L;
		Double total = 49.52;
		String orderedOn = "2021-03-22";
		String[] dateArr = orderedOn.split("-", 0);
		int year = Integer.parseInt(dateArr[0]);
		int month = Integer.parseInt(dateArr[1]);
		int day = Integer.parseInt(dateArr[2]);
		LocalDate date = LocalDate.of(year, month, day);
		
		final Order o = new Order(customerId, total, date);
		
		Mockito.when(utils.getLong()).thenReturn(customerId);
		Mockito.when(utils.getDouble()).thenReturn(total);
		Mockito.when(utils.getString()).thenReturn("2021-03-22");
		Mockito.when(dao.create(o)).thenReturn(o);

		assertEquals(o, controller.create());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(utils, Mockito.times(1)).getDouble();
		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(dao, Mockito.times(1)).create(o);
	}
	
	@Test
	public void readTest() {
		Order o = new Order(213L, 4L, 94.24, LocalDate.of(2021, 6, 1));
		
		Mockito.when(utils.getLong()).thenReturn(213L);
		Mockito.when(dao.read(213L)).thenReturn(o);
		
		assertEquals(o, controller.read());
		
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).read(213L);
		
	}

	@Test
	public void readAllTest() {
		ArrayList<Order> orders = new ArrayList<Order>();
		orders.add(new Order((long) 21, 14.99, LocalDate.of(2021, 4, 25)));

		Mockito.when(dao.readAll()).thenReturn(orders);

		assertEquals(orders, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

	@Test
	public void updateTest() {
		Order o = new Order((long) 22, (long) 44, 199.32, LocalDate.of(2020, 12, 22));

		Mockito.when(this.utils.getLong()).thenReturn((long) 22, (long) 44);
		Mockito.when(this.utils.getDouble()).thenReturn(199.32);
		Mockito.when(this.utils.getString()).thenReturn("2020-12-22");
		Mockito.when(this.dao.update(o)).thenReturn(o);
		
		Order updated = controller.update();
		
		assertEquals(updated, o);

		Mockito.verify(this.utils, Mockito.times(2)).getLong();
		Mockito.verify(this.utils, Mockito.times(1)).getDouble();
		Mockito.verify(this.utils, Mockito.times(1)).getString();
		Mockito.verify(this.dao, Mockito.times(1)).update(updated);
	}
	
	@Test
	public void deleteTest() {
		Order o = new Order((long) 52, (long) 22, 22.88, LocalDate.of(2021, 2, 9));
		
		Mockito.when(this.utils.getLong()).thenReturn((long) 52);
		Mockito.when(this.dao.delete(52)).thenReturn(1);
		
		int result = controller.delete();
		assertEquals(1, result);
		
		Mockito.verify(this.utils, Mockito.times(1)).getLong();
		Mockito.verify(this.dao, Mockito.times(1)).delete(52);
	}

}