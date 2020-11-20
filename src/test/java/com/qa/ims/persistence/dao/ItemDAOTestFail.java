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
import com.qa.ims.utils.DBUtils;

public class ItemDAOTestFail {
	
	private final ItemDAO DAO = new ItemDAO();

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
		final String ITEM_NAME = "chess set";
		final double ITEM_PRICE = 12.99;
		final long QUANTITY = 7l, ID = 5l;
		final Item created = new Item(ID, ITEM_NAME, ITEM_PRICE, QUANTITY);
		assertNull(DAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<Item> expected = new ArrayList<>();
		final Item harmonica = new Item(1l, "harmonica", 1200.99, 8l);
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertNull(DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		assertNull(DAO.readItem(ID));
	}

	@Test
	public void testUpdate() {
		final String ITEM_NAME = "chess set";
		final double ITEM_PRICE = 12.99;
		final long QUANTITY = 7l, ID = 5l;
		final Item updated = new Item(ID, ITEM_NAME, ITEM_PRICE, QUANTITY);
		assertNull(DAO.update(updated));
	}

	@Test
	public void testDelete() {
		assertEquals(0, DAO.delete(2));
	}
}
