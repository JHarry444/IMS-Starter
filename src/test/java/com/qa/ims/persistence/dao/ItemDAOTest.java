package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAOTest {

	private final ItemDAO DAO = new ItemDAO();

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
		final Item created = new Item(2L, "POST HUMANS: SURVIVAL HORROR", "Bring Me The Horizon", 20201030.0, 10.99, 745.0);
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<Item> expected = new ArrayList<>();
		expected.add(new Item(1L, "SAWAYAMA","Rina Sawayama", 20200417.0, 9.99, 300.0));
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertEquals(new Item(1L, "SAWAYAMA","Rina Sawayama", 20200417.0, 9.99, 300.0), DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long productID = 1L;
		assertEquals(new Item(productID, "SAWAYAMA","Rina Sawayama", 20200417.0, 9.99, 300.0), DAO.readItem(productID));
	}

	@Test
	public void testUpdate() {
		final Item updated = new Item(1L, "SAWAYAMA","Rina Sawayama", 20200417.0, 9.99, 300.0);
		assertEquals(updated, DAO.update(updated));

	}

	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}
}