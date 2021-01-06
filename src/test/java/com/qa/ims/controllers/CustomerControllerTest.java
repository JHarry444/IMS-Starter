package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.CustomerController;
import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private CustomerDAO dao;

	@InjectMocks
	private CustomerController controller;

	@Test
	public void testCreate() {
		final String firstName = "Abigail", lastName = "Jones", email = "aj@gmail.com", addressLine1 = "25 Crown Street", postcode = "SW1Y 5JH" ;
		final Customer created = new Customer(firstName, lastName, email, addressLine1, postcode);

		Mockito.when(utils.getString()).thenReturn(firstName, lastName, email, addressLine1, postcode);
		Mockito.when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(2)).getString();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}

	@Test
	public void testReadAll() {
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer("Claude", "Duvalier", "cd1990@gmail.com", "65 Hudson Street", "DG6 6PZ"));

		Mockito.when(dao.readAll()).thenReturn(customers);

		assertEquals(customers, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

	@Test
	public void testUpdate() {
		Customer updated = new Customer("Tasha", "Lee", "tasha.lee@outlook.com", "35 Crown Street", "W12 4WW");

		Mockito.when(this.utils.getLong()).thenReturn((long) 001);
		Mockito.when(this.utils.getString()).thenReturn(updated.getFirstName(), updated.getLastName());
		Mockito.when(this.dao.update(updated)).thenReturn(updated);

		assertEquals(updated, this.controller.update());

		Mockito.verify(this.utils, Mockito.times(1)).getLong();
		Mockito.verify(this.utils, Mockito.times(2)).getString();
		Mockito.verify(this.dao, Mockito.times(1)).update(updated);
	}

	@Test
	public void testDelete() {
		final long ID = 001;

		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(dao.delete(ID)).thenReturn(1);

		assertEquals(1L, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(ID);
	}

}
