package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.Utils;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class CustomerController implements CrudController<Customer> {

	public static final Logger LOGGER = LogManager.getLogger();

	private CustomerDAO customerDAO;
	private Utils utils;

	public CustomerController(CustomerDAO customerDAO, Utils utils) {
		super();
		this.customerDAO = customerDAO;
		this.utils = utils;
	}

	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Customer> readAll() {
		List<Customer> customers = customerDAO.readAll();
		for (Customer customer : customers) {
			LOGGER.info(customer);
		}
		return customers;
	}

	/**
	 * Creates a customer by taking in user input
	 */
	@Override
	public Customer create() {
		LOGGER.info("Please enter a first name:");
		String firstName = utils.getString();
		LOGGER.info("Please enter a surname:");
		String surname = utils.getString();
		Customer customer = customerDAO.create(new Customer(firstName, surname));
		LOGGER.info(String.format("Customer %s %s created%nThis customer's ID is: %d", customer.getFirstName(), customer.getSurname(), customer.getId()));
		return customer;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Customer update() {
		LOGGER.info("Please enter the ID of the customer you would like to update:");
		Long id = utils.getLong();
		LOGGER.info("Please enter a new first name:");
		String firstName = utils.getString();
		LOGGER.info("Please enter a new surname:");
		String surname = utils.getString();
		Customer customer = customerDAO.update(new Customer(id, firstName, surname));
		LOGGER.info(String.format("Customer %s %s updated%nThis customer's ID is: %d", customer.getFirstName(), customer.getSurname(), customer.getId()));
		return customer;
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the ID of the customer you would like to delete:");
		Long id = utils.getLong();
		Customer customer = customerDAO.read(id);
		LOGGER.info(String.format("Are you sure you want to delete %s %s?%nY for Yes, N for No", customer.getFirstName(), customer.getSurname()));
		String input = utils.getString();
		if (input.strip().equalsIgnoreCase("Y")) {
			LOGGER.info("Customer deleted.");
			return customerDAO.delete(id);
		} else {
			LOGGER.info("Deletion canceled.");
		}
		return 0;
	}

}
