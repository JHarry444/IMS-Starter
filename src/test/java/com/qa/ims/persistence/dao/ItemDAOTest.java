package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAOTest {
	
	private final ItemDAO DAO = new ItemDAO();
	
	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() {
		final Item created = new Item(1l, "eggs", 2);
		assertEquals(created, DAO.create(created));
	}
	
	@Test
	public void testReadAll() {
		List<Item> expected = new ArrayList<>();
		expected.add(new Item(1l, "eggs", 2));
		assertEquals(expected, DAO.readAll());
	}
	
	@Test
	public void testReadLatest() {
		assertEquals(new Item(1l, "eggs", 2), DAO.readLatest());
	}
	
	@Test
	public void testRead() {
		final long item_id=1L;
		assertEquals(new Item(item_id, "eggs", 2), DAO.read(item_id));
	}
	
	@Test
	public void testUpdate() {
		final Item updated = new Item(1l, "pasta", 2);
		assertEquals(null, DAO.update(updated));
	}
	
	@Test
	public void testDelete() {
		Item created = new Item(1l, "bacon", 2);
		DAO.create(created);
		assertEquals(1, DAO.delete(1));
	}
	
	@Test
	public void ExceptionHandlerTest() {
		DBUtils.connect("db.url=jdbc:h2:~/im");
		Item created = new Item(1l, "eggs", 3);
		DAO.create(created);
		assertEquals(0, DAO.delete(1));
		
	}

}
