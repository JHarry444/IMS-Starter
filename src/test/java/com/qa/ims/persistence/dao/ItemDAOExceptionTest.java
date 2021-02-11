package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;


import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

import org.junit.Test;

public class ItemDAOExceptionTest {

	private final ItemDAO itemDAO = new ItemDAO();
	
	@Before
	public void setup() {
		DBUtils.connect("Fail");
	}
	
	@Test
	public void testCreate() {
		final Item created = new Item(2L, "Mouse", 8.0, "Clicker");
		assertEquals(null, itemDAO.create(created));
	}
	

	@Test
	public void testReadAll() {
		List<Item> expected = new ArrayList<>();
		expected.add(new Item(1L, "Keyboard", 10.0, "Tapper"));
		assertEquals(new ArrayList<>(), itemDAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertEquals(null, itemDAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 1L;

		assertEquals(null, itemDAO.read(ID));
	}
	
	@Test
	public void testUpdate() {
		final Item updated = new Item(1L, "Mouse", 8.0, "Clicker");
		assertEquals(null, itemDAO.update(updated));
	}
	
	@Test
	public void testDelete() {
		assertEquals(0, itemDAO.delete(1));
	} 
	
}
