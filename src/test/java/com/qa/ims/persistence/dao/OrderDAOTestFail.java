package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTestFail {
	private final OrderDAO DAO = new OrderDAO();

	@BeforeClass
	public static void init() {
		DBUtils.connect("fail");
	}

	@Before
	public void setup() {
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		final Order created = new Order(2L, 1, 1, 2L, 0);
		assertNull(DAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<Order> expected = new ArrayList<>();
		expected.add(new Order(1L, 1, 1, 1L, 0));
		assertNotNull(DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertNull(DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		assertNull(DAO.read(ID));
	}

	@Test
	public void testUpdate() {
		final Order update = new Order(1L, 1, 1, 1L, 10);
		assertNull(DAO.update(update));

	}
	@Test
	public void testDeleteOrder() {
		assertEquals(0,DAO.delete(1));
	}
	
	@Test
	public void testDeleteItem() {
		final Order delete = new Order (2L,1,1,2L,100);
		DAO.create(delete);
		assertEquals(0,DAO.deleteItem(2 , 1));
	} 
}
