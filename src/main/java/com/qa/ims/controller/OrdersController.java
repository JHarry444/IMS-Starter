package com.qa.ims.controller;

import java.math.BigDecimal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrdersDAO;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.Utils;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class OrdersController implements CrudController<Orders> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrdersDAO ordersDAO;
	private Utils utils;

	public OrdersController(OrdersDAO ordersDAO, Utils utils) {
		super();
		this.ordersDAO = ordersDAO;
		this.utils = utils;
	}

	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Orders> readAll() {
		List<Orders> orders = ordersDAO.readAll();
		for (Orders order : orders) {
			LOGGER.info(order.toString());
		}
		return orders;
	}

	/**
	 * Creates a customer by taking in user input
	 */
	@Override
	public Orders create() {
		LOGGER.info("Please enter the Customer ID");
		Long customerID = utils.getLong();
		LOGGER.info("Please enter the Product ID ");
		Long productID = utils.getLong();
		LOGGER.info("Please enter the quantity of items purchased");
		Double orderQty = utils.getDouble();
		LOGGER.info("Please enter the total price of items purchased");
		BigDecimal totalPrice = utils.getBigDecimal();
		Orders order = ordersDAO.create(new Orders(customerID, productID, orderQty, totalPrice));
		LOGGER.info("Order created");
		return order;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Orders update() {
		LOGGER.info("Please enter the ID of the order you would like to update");
		Long orderID = utils.getLong();
		LOGGER.info("Please enter the Customer ID");
		Long customerID = utils.getLong();
		LOGGER.info("Please enter the Product ID ");
		Long productID = utils.getLong();
		LOGGER.info("Please enter the quantity of items purchased");
		Double orderQty = utils.getDouble();
		LOGGER.info("Please enter the total price of items purchased");
		BigDecimal totalPrice = utils.getBigDecimal();
		Orders order = ordersDAO.create(new Orders(orderID, customerID, productID, orderQty, totalPrice));
		LOGGER.info("Order updated");
		return order;
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the ID of the order you would like to delete");
		Long orderID = utils.getLong();
		return ordersDAO.delete(orderID);
	}

}
