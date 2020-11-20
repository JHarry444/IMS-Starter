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
		DBUtils.connect("root", "root");
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
		
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<Item> expected = new ArrayList<>();
		expected.add(new Item(1l, "harmonica", 1200.99, 8l));
		expected.add(new Item(2l, "wheelie bin", 2.99, 84528l));
		expected.add(new Item(3l, "your mother", 0.99, 1l));
		expected.add(new Item(4l, "nike airs", 40.99, 74l));
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertEquals(new Item(4l, "nike airs", 40.99, 74l), DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals(new Item(1l, "harmonica", 1200.99, 8l), DAO.readItem(ID));
	}

	@Test
	public void testUpdate() {
		final Item updated = new Item(1l, "updated", 0.42, 1l);
		assertEquals(updated, DAO.update(updated));

	}

	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(3));
	}
}
