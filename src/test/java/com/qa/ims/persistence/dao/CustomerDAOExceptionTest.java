package com.qa.ims.persistence.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.DBUtils;

public class CustomerDAOExceptionTest {

private final CustomerDAO custDAO = new CustomerDAO();
	
	
	@Rule
	public ExpectedException thrown = ExpectedException.none(); 

	@Before
	public void setup() {
		DBUtils.connect("Fail");
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}

	@Test
	public void testCreate() {
		final Customer created = new Customer(2L, "chris", "perrins");
		assertEquals(null, custDAO.create(created));
		
	}	  

	@Test
	public void testReadAll() {		
		assertEquals(new ArrayList<>(), custDAO.readAll());
	}
	
	@Test
	public void testReadLatest() {
		assertEquals(null, custDAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		
		assertEquals(null, custDAO.read(ID));
	}

	@Test
	public void testUpdate() {
		final Customer updated = new Customer(1L, "chris", "perrins");
	
		assertEquals(null, custDAO.update(updated));

	}
	
	@Test
	public void testDelete() {
		assertEquals(0, custDAO.delete(1));
	}
}
