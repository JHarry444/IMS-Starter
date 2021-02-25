package com.qa.ims.persistence.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAOEmptyTest {

    private final ItemDAO DAO = new ItemDAO();

	@BeforeAll
	public static void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema-empty.sql");
	}

	@Test
	public void testReadAll() {
		List<Item> expected = new ArrayList<>();
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertEquals(null, DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals(null, DAO.read(ID));
	}

	@Test
	public void testUpdate() {
		final Item updated = new Item(2L, "pear", 4.50D);
		assertEquals(null, DAO.update(updated));

	}

	@Test
	public void testDelete() {
		assertEquals(0, DAO.delete(1));
	}
}
