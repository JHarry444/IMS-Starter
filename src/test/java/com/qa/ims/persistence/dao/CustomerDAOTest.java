package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.DBUtils;

public class CustomerDAOTest {

	private final CustomerDAO DAO = new CustomerDAO();

	@BeforeClass
	public static void init() {
		DBUtils.connect("root", "pass");
	}

	@Before
	public void setup() {
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		final Customer created = new Customer(2L, "Chris", "Perrins", "cperrins@gmail.com", "61 Kingsway North", "LL13 1HE");
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<Customer> expected = new ArrayList<>();
		expected.add(new Customer(1L, "Jordan", "Harrison", "json@gmail.com", "61 Kingsway North", "LL13 1HE"));
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertEquals(new Customer(1L, "Jordan", "Harrison", "json@gmail.com", "61 Kingsway North", "LL13 1HE"), DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long customerID = 1L;
		assertEquals(new Customer(customerID, "Jordan", "Harrison", "json@gmail.com", "61 Kingsway North", "LL13 1HE"), DAO.readCustomer(customerID));
	}

	@Test
	public void testUpdate() {
		final Customer updated = new Customer(1L, "Jordan", "Harrison", "json@gmail.com", "61 Kingsway North", "LL13 1HE");
		assertEquals(updated, DAO.update(updated));

	}

	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}
}