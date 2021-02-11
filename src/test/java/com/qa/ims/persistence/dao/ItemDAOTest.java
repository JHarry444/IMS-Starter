package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.rmi.ServerRuntimeException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
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
		final Item created = new Item(2L,"Pizza",100);
		assertEquals(created, DAO.create(created));
	}
	@Test
	public void testReadAll() {
		List<Item> item = new ArrayList<>();
		item.add(new Item(1L, "Pizza", 10));
		assertEquals(item, DAO.readAll());
	}
	@Test
	public void testReadLatest() {
		assertEquals(new Item(1L, "Pizza", 10), DAO.readLatest());
	}
	@Test
	public void testUpdate() {
		final Item updated = new Item(1L, "Pizza", 50);
		assertEquals(updated, DAO.update(updated));

	}
	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}
	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals(new Item(ID, "Pizza", 10), DAO.read(ID));
	}
	@Test
	public void testReadException() {
		final long ID = 10L;
		assertEquals(null,DAO.read(ID));
	}
	@Test
	public void testUpdateException() {
		final Item ID = new Item(100L,"Pizza",10);
		assertEquals(null,DAO.update(ID));
	}
	@Test
	public void testCreateException() {
		final Item created = new Item(2L,"Pizza",100);
		assertEquals(created, DAO.create(created));
	}
}