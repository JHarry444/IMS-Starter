package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

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
		Order read = new Order(2L,2l);
		assertEquals(read,DAO.readLatest());
	}
	@Test
	public void TestCreate() {
		Order created = new Order(2L,2l);
		assertEquals(created,DAO.create(2L,2L));
	}
	@Test
	public void TestRemove() {
		Order removed = new Order(2L,2l);
		assertEquals(removed,DAO.remove(1L, 1l));
	}
	@Test
	public void TestCust() {
		Order cust = new Order(2L,1L);
		assertEquals(cust,DAO.cust(2L, 1L));
	}
	@Test
	public void TestReadItemsByOrder() {
		Item item1 = new Item(1l,"cheese",10.98);
		List<Item> items = new ArrayList<Item>();
		items.add(item1);
		items.add(item1);
		assertEquals(items,DAO.readItemsByOrder(1l));
	}
}
