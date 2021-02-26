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
		final Item created = new Item(2l, "apple", 2.5f);
		assertEquals(created, DAO.create(created));
	}
	
	@Test
	public void testReadAll() {
		List<Item> expected = new ArrayList<>();
		expected.add(new Item(1l, "apple", 2.5f));
		
		assertEquals(expected, DAO.readAll());
		
	}
	
	@Test
	public void testReadLatest() {
		assertEquals(new Item(1l, "apple", 2.5f), DAO.readLatest());
	}
	
	@Test
	public void testRead() {
		final long item_id=1L;
		assertEquals(new Item(item_id, "apple", 2.5f), DAO.read(item_id));
	}
	
	@Test
	public void testUpdate() {
		final Item updated = new Item(1l, "apple", 2.5f);
		assertEquals(updated, DAO.update(updated));
	}
	
	@Test
	public void testDelete() {
		Item created = new Item(1l, "apple", 2.5f);
		DAO.create(created);
		assertEquals(1, DAO.delete(1));
	}
	
	@Test
	public void ExceptionHandlerTest() {
		DBUtils.connect("db.url=jdbc:h2:~/im");
		Item created = new Item(1l, "apple", 2.5f);
		DAO.create(created);
		assertEquals(0, DAO.delete(1));
	}
	
	@Test
	public void ExceptionHandlerTestTwo() {
		DBUtils.connect("db.url=jdbc:h2:~/imttt");
		final Item updated = new Item(1l, "apple", 2.5f);
		assertEquals(updated, DAO.update(updated));
	}
	
	@Test
	public void ExceptionHandlerTestThree() {
		DBUtils.connect("db.url=jdbc:h2:~/imfff");
		assertEquals(new Item(1l, "apple", 2.5f), DAO.read(1l));
	}
	
	@Test
	public void ExceptionHandlerTestFour() {
		DBUtils.connect("db.url=jdbc:h2:~/imfff");
		assertEquals(new Item(1l, "apple", 2.5f), DAO.readLatest());
	}
	
	@Test
	public void ExceptionHandlerTestFive() {
		DBUtils.connect("db.url=jdbc:h2:~/imfff");
		assertEquals(new Item(1l, "apple", 2.5f), DAO.readAll());
	}

}
