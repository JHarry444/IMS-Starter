package com.qa.ims.persistence.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTest {
    
    private final OrderDAO DAO = new OrderDAO();

	@BeforeAll
	public static void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema-order.sql", "src/test/resources/sql-data-order.sql");
	}

	@Test
	public void testCreate() {
		final Order created = new Order(2L, 1L);
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<Order> expected = new ArrayList<>();
		expected.add(new Order(1L, 1L));
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertEquals(new Order(1L, 1L), DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals(new Order(1L, 1L), DAO.read(ID));
	}

	@Test
	public void testUpdate() {
		final Order updated = new Order(2L, 2L);
		assertEquals(updated, DAO.update(updated));
	}

	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}

}
