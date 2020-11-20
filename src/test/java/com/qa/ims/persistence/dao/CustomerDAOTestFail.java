package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
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
		DBUtils.connect("root", "fail");
	}

	@Before
	public void setup() {
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		final Customer created = new Customer(4L, "chris", "perrins");
		assertNull(DAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<Customer> expected = new ArrayList<>();
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertNull(DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		assertNull(DAO.readCustomer(ID));
	}

	@Test
	public void testUpdate() {
		final Customer updated = new Customer(1L, "chris", "perrins");
		assertNull(DAO.update(updated));
	}

	@Test
	public void testDelete() {
		assertEquals(0, DAO.delete(2));
	}
}
