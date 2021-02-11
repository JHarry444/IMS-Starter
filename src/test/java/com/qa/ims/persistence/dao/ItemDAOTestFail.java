package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAOTestFail {
		private final ItemDAO DAO = new ItemDAO();
		
		@BeforeClass
		public static void init() {
		DBUtils.connect("fail");
		}
		
		@Before
		public void setup() {
			DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
		}
		@Test
		public void testCreate() {
			final Item created = new Item(2L,"Pizza",100);
			assertNull(DAO.create(created));
		}
		@Test
		public void testReadAll() {
			List<Item> item = new ArrayList<>();
			item.add(new Item(1L, "Pizza", 10));
			assertNotNull(DAO.readAll());
		}
		@Test
		public void testReadLatest() {
			assertNull(DAO.readLatest());
		}
		@Test
		public void testUpdate() {
			final Item updated = new Item(1L, "Pizza", 50);
			assertNull(DAO.update(updated));

		}
		@Test
		public void testDelete() {
			assertNotNull(DAO.delete(1));
		}
		@Test
		public void testRead() {
			final long ID = 1L;
			assertNull(DAO.read(ID));
		}
}

