package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.DBUtils;

public class CustomerDAOTest {

	private final CustomerDAO DAO = new CustomerDAO();

	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		final Customer created = new Customer(2L, "chris", "perrins");
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<Customer> expected = new ArrayList<>();
		expected.add(new Customer(1L, "jordan", "harrison"));
		assertEquals(expected, DAO.readAll());
		
	}

	@Test
	public void testReadLatest() {
		assertEquals(new Customer(1L, "jordan", "harrison"), DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals(new Customer(ID, "jordan", "harrison"), DAO.read(ID));
	}

	@Test
	public void testUpdate() {
		Customer updated = new Customer(1L, "chris", "perrins");
		assertEquals(updated, DAO.update(updated));
	}

	@Test
	public void testDelete() {
		Customer created = new Customer(1l, "nik", "pap");
		DAO.create(created);
		assertEquals(1, DAO.delete(1));
	}
	
	@Test
	public void ExceptionHandlerTest() {
		DBUtils.connect("db.url=jdbc:h2:~/im");
		Customer created = new Customer(1l, "nik", "pap");
		DAO.create(created);
		assertEquals(0, DAO.delete(1));
	}
	
	@Test
	public void ExceptionHandlerTestTwo() {
		DBUtils.connect("db.url=jdbc:h2:~/imttt");
		final Customer updated = new Customer(1l, "nik", "pap");
		assertEquals(updated, DAO.update(updated));
	}
	
	@Test
	public void ExceptionHandlerTestThree() {
		DBUtils.connect("db.url=jdbc:h2:~/imfff");
		assertEquals(new Customer(1l, "nik", "pap"), DAO.readAll());
	}
	
	@Test
	public void ExceptionHandlerTestForth() {
		DBUtils.connect("db.url=jdbc:h2:~/imfff");
		assertEquals(new Customer(1l, "nik", "pap"), DAO.read(null));
	}
	
	@Test
	public void ExceptionHandlerTestFifth() {
		DBUtils.connect("db.url=jdbc:h2:~/imfff");
		assertEquals(new Customer(1l, "nik", "pap"), DAO.readLatest());
	}
}
