package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;


import org.junit.Before;

import org.junit.Test;
import org.mockito.Mockito;


import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;


public class OrderDAOTest {

	private final OrderDAO orderDAO = new OrderDAO();

	@Before
	public void setup() {
		// DBUtils.connect("Fail");
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
		
	}


	@Test
	public void testCreateUpdateOrder() {
		Order created = new Order(2L, 1L);
		assertEquals(created, orderDAO.createUpdateOrder(created, true));
	}

	@Test
	public void testExceptionCreateUpdateOrder() {
		Order updated = new Order(2L, 3L);
		assertEquals(null, orderDAO.createUpdateOrder(updated, false));
	}

	@Test
	public void testCreateUpdateOrderItem() {
		Order created = new Order(1L, 1L, 4L);
		assertEquals(new Order(1L,1L), orderDAO.createUpdateOrderItem(created, Mockito.anyBoolean()));
		
	}

	@Test
	public void testReadAllItems() {
		List<Order> expected = new ArrayList<>();
		expected.add(new Order(1L, 1L));
		
		assertEquals(new ArrayList<>(), orderDAO.readAllItems(true));
		assertEquals(expected, orderDAO.readAllItems(false));
		
	}

	@Test
	public void testReadLatest() {
		assertEquals(new Order(1L, 1L), orderDAO.readLatestItem(false));
		assertEquals(null, orderDAO.readLatestItem(true));
	}

	@Test
	public void testRead() {
		final long Id = 1L;
		assertEquals(null, orderDAO.read(Id, false));
	}

	@Test
	public void testReadSpecific() {
		List<Order> expected = new ArrayList<Order>();
		expected.add(new Order(1L, "", "", 1L, 1L, "", 0.0, "", 0.0));
		assertEquals(expected.get(0).itemsToString(), orderDAO.readSpecific(expected.get(0).getOrderId()));
	}

	@Test
	public void testDeleteNullOrders() {
		assertEquals(0, orderDAO.deleteNullOrders());
	}

	@Test
	public void testDelete() {
		assertEquals(0, orderDAO.delete(1));
	}

	@Test
	public void testDeleteOrderItem() {
		assertEquals(0, orderDAO.deleteOrderItem(1, 1));
	}

	@Test
	public void testCalculateCost() {
		List<Order> oi = new ArrayList<>();
		oi.add(new Order(1L, "", "", 1L, 1L, "", 0.0, "", 0.0));
		assertEquals(0.0, orderDAO.calculateCost(oi), 0);
	}

	@Test
	public void testBlanks() {
		assertEquals(null, orderDAO.readAll());
		assertEquals(null, orderDAO.create(null));
		assertEquals(null, orderDAO.update(null));
		assertEquals(null, orderDAO.read(null));
	}
}
