package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTest {
	
	private final OrderDAO DAO = new OrderDAO();
	
	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() {
		final Order created = new Order(1l, 2, 2);
		assertEquals(created, DAO.create(created));
	}
	
	@Test
	public void testReadAll() {
		List<Order> expected = new ArrayList<>();
		expected.add(new Order(1l, 2, 2));
		assertEquals(expected, DAO.readAll());
	}
	
	@Test
	public void testReadLatest() {
		assertEquals(new Order(1l, 2, 2), DAO.readLatest());
	}
	
	@Test
	public void testRead() {
		final long order_id = 1L;
		assertEquals(new Order(order_id, 2, 2), DAO.read(order_id));
	}
	
	@Test
	public void testUpdate() {
		Order updated = new Order(1L, 2, 2);
		assertEquals(updated, DAO.update(updated));
	}
	
	@Test
	public void testDelete() {
		Order created = new Order(1L, 2, 2);
		DAO.create(created);
		assertEquals(1, DAO.delete(1));
	}
	
	@Test
	public void ExceptionHandlerTest() {
		DBUtils.connect("db.url=jdbc:h2:~/im");
		Order created = new Order(1l, 2, 3);
		DAO.create(created);
		assertEquals(0, DAO.delete(1));
		
	}

}
