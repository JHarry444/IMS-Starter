package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.DBUtils;

public class CustomerDAOTestFail {
	private final CustomerDAO DAO = new CustomerDAO();
	
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
		final Customer created = new Customer(2L, "chris", "perrins");
		assertNull(DAO.create(created));
	}
	@Test
	public void testReadAll() {
		List<Customer> expected = new ArrayList<>();
		expected.add(new Customer(1L, "jordan", "harrison"));
		assertNotNull(DAO.readAll());
}
	@Test
	public void testReadLatest() {
		assertNull(DAO.readLatest());
	}
	@Test
	public void testUpdate() {
		final Customer updated = new Customer(1L, "chris", "perrins");
		assertNull(DAO.update(updated));
	}
	@Test
	public void testDelete() {
		assertNotNull(DAO.delete(1));
	}
}
