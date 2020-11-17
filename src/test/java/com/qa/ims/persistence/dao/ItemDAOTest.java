package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAOTest {
	private final ItemDAO DAO = new ItemDAO();
	
	@BeforeClass
	public static void init() {
		DBUtils.connect("root", "f4T!che3sE");
	}
	@Before
	public void setup() {
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	@Test
	public void testCreate() {
		final Item created = new Item(3L,"beans",20.00D);
		assertEquals(created,DAO.create(created));
	}
	
	@Test
	public void testreadItem() {
		final Item read = new Item(1L,"cheese",10.98D);
		assertEquals(read,DAO.readItem(1L));
	}
	@Test
	public void testreadAll() {
		List<Item> readall = new ArrayList<Item>();
		readall.add(new Item(1L,"cheese",10.98D));
		readall.add(new Item(2L,"chips",98.00D));
		assertEquals(readall,DAO.readAll());
	}
	@Test
	public void testCreateExcept() {
		final Item exc = new Item(3L,"bena';;';",20.00D);
		assertEquals(null,DAO.create(exc));
	}
	
	@Test
	public void testUpdate() {
		final Item updated = new Item(1L,"chips",25.00D);
		assertEquals(updated,DAO.update(new Item(1L,"chips",25.00D)));
	}
	@Test
	public void testUpdateExc() {
		final Item updated = new Item(1L,"chips';';';",25.00D);
		assertEquals(null,DAO.update(updated));
	}
	@Test
	public void testDelete() {
		assertEquals(0,DAO.delete(1L));
	}
}

	
