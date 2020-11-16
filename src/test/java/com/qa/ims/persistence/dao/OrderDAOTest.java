package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;


public class OrderDAOTest {
	private final OrderDAO DAO = new OrderDAO();
	
	@BeforeClass
	public static void init() {
		DBUtils.connect("root", "f4T!che3sE");
	}
	@Before
	public void setup() {
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	@Test
	public void TestCreate() {
		final Order created = new Order(3L,2L);
		assertEquals(created,DAO.create(new Order(3L,2L)));
		
	}
	
}
