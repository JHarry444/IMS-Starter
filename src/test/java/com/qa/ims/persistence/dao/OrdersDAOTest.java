package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.DBUtils;

public class OrdersDAOTest {

	private final OrdersDAO DAO = new OrdersDAO();

	@BeforeClass
	public static void init() {
		DBUtils.connect("root", "pass");
	}

	@Before
	public void setup() {
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		final Orders created = new Orders(1L, 1L, 1.0, 13.00, false);
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<Orders> expected = new ArrayList<>();
		expected.add(new Orders(1L, 1L, 1.0, 13.00, false));
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertEquals(new Orders(1L, 1L, 1L, 1.0, 13.00, false), DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long orderID = 1L;
		assertEquals(new Orders(orderID, 1L, 1L, 1.0, 13.00, false), DAO.readOrders(orderID));
	}

	@Test
	public void testUpdate() {
		final Orders updated = new Orders(1L, 1L, 1L, 1.0, 13.00, true);
		assertEquals(updated, DAO.update(updated));

	}

	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}
}