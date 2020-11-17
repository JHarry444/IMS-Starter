package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class Order_ItemDAOTest {
	private final Order_ItemDAO DAO = new Order_ItemDAO();
	
	@BeforeClass
	public static void init() {
		DBUtils.connect("root", "f4T!che3sE");
	}
	@Before
	public void setup() {
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void TestReadLatest() {
		Item read = new Item(1L,"cheese",10.98D);
		assertEquals(read,DAO.readLatest());
	}
	@Test
	public void TestCreate() {
		Item created = new Item(2L,"chips",98.0D);
		assertEquals(created,DAO.create(2L,2L));
	}
	@Test
	public void TestRemove() {
		Item removed = new Item(1L,"cheese",10.98D);
		assertEquals(removed,DAO.remove(2L, 1l));
	}
	@Test
	public void TestCust() {
		Order cust = new Order(2L,1L);
		assertEquals(cust,DAO.cust(2L, 1L));
	}
	
}
