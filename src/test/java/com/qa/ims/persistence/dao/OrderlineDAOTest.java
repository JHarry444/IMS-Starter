package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Orderline;
import com.qa.ims.utils.DBUtils;

public class OrderlineDAOTest {

	private final OrderlineDAO DAO = new OrderlineDAO();

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
		final Orderline created = new Orderline(1L, 1L);
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<Orderline> expected = new ArrayList<>();
		expected.add(new Orderline(1L, 1L));
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertEquals(new Orderline(1L, 1L), DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long orderlineID = 1L;
		assertEquals(new Orderline(orderlineID, 1L, 1L), DAO.readOrderline(orderlineID));
	}

	@Test
	public void testUpdate() {
		final Orderline updated = new Orderline(1L, 1L, 1L);
		assertEquals(updated, DAO.update(updated));

	}

	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}
}