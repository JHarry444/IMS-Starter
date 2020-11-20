package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTestFail {
	private final OrderDAO DAO = new OrderDAO();

	@BeforeClass
	public static void init() {
		DBUtils.connect("root", "fail");
	}

	@Before
	public void setup() {
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		final Order created = new Order(4L, 3l, new ArrayList<>());
		assertNull(DAO.create(created));
	}
	@Test
	public void testReadAll() {
		List<Customer> expected = new ArrayList<>();
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertNull(DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		assertNull(DAO.readOrder(ID));
	}
	@Test
	public void testReadLines() {
		final long ID = 1L;
		assertNull(DAO.readLines(ID));
	}

	@Test
	public void testUpdate() {
		final Order updated = new Order(4L, 3l, new ArrayList<>());
		assertNull(DAO.update(updated));
	}

	@Test
	public void testDelete() {
		assertEquals(0, DAO.delete(2));
	}
	@Test
	public void testDeleteLines() {
		assertEquals(0, DAO.deleteLine(2l, 3l));
	}
	@Test
	public void testDeleteOrderLines() {
		assertEquals(0, DAO.deleteOrderLines(2l));
	}
}
