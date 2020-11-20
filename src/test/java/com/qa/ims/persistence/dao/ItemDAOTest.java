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
		DBUtils.connect("root", "Cod4life");
	}
	
	@Before
	public void setup() {
	
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	
	}
	
	@Test
	public void testCreate() {
		final Item created = new Item(2L, "Pencil", 2.99);
		assertEquals(created, DAO.create(created));
	}

	
	@Test
	public void testReadAll() {
		List<Item> expected = new ArrayList<>();
		expected.add(new Item(1L, "Pencil", 2.99));
		assertEquals(expected, DAO.readAll());
	}
	
	@Test
	public void testReadLatest() {
		assertEquals(new Item(1L, "Pencil", 2.99), DAO.readLatest());
	}
	
	@Test
	public void testRead() {
		final long Item_id = 1L;
		assertEquals(new Item(Item_id, "Pencil", 2.99), DAO.readItem(Item_id));
	}
	
	@Test
	public void testUpdate() {
		final Item updated = new Item(1L, "Chair", 75.00);
		assertEquals(updated, DAO.update(updated));
	}
	
	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}
}



















