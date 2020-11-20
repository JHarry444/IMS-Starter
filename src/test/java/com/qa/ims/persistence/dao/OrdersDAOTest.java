package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.DBUtils;

public class OrdersDAOTest {
	private final OrdersDAO DAO = new OrdersDAO();
	
	@BeforeClass
	public static void init() {
		DBUtils.connect("root", "Cod4life");
	}
	
	@Before
	public void setup() {
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreateCust() {
		final Orders created = new Orders(2L, 1L);
		assertEquals(created, DAO.create(created));
	} 
	
//	@Test
//	public void testCreate() {
//		final Orders created = new Orders(5L, 4L);
//		assertEquals(created, DAO.create(new Orders(5L, 4L)));
//	}
	
	
//	@Test
//	public void testReadAll() {
//		List<Orders> expected = new ArrayList<Orders>();
//		expected.add(new Orders(5L,4L));
//		assertEquals(expected, DAO.readAll());
//	}
//	
//	@Test
//	public void testDelete() {
//		assertEquals(1, DAO.delete(1L));
//	}
//
}
