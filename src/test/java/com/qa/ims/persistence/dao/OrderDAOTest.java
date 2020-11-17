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

	@Test
	public void TestUpdate() {
		assertEquals(null,DAO.update(new Order(2l,2l)));
	}
	
	@Test
	public void TestDelete() {
		assertEquals(1,DAO.delete(1l));
	}
	@Test
	public void TestreadSingle() {
		Order read = new Order(1L,1L,"tom smith","cheese, cheese",21.96D);
		assertEquals(read,DAO.readSingle(1L));
	}
	@Test
	public void TestreadALL() {
		List<Order> all = new ArrayList<Order>();
		Order O1 = new Order(1L,1L,"tom smith","cheese, cheese",21.96D);
		Order O2 = new Order(2L,2L,"jordan harrison","cheese",10.98D);
		all.add(O1);
		all.add(O2);
		assertEquals(all,DAO.readAll());
	}
}
