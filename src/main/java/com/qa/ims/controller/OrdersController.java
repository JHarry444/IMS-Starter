package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrdersDAO;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.Utils;

/**
 * Takes in item details for CRUD functionality
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
	 * Reads all items to the logger
	 */
	@Override
	public List<Orders> readAll() {
		List<Orders> order = ordersDAO.readAll();
		for (Orders orders : order) {
			LOGGER.info(orders.toString());
		}
		return order;
	}

	/**
	 * Creates an item by taking in user input
	 */
	@Override
	public Orders create() {
		LOGGER.info("Please enter the customer ID");
		Long customerID = utils.getLong();
		LOGGER.info("Please enter the product ID");
		Long productID = utils.getLong();
		LOGGER.info("Please enter the number of albums ordered");
		Double productQty = utils.getDouble();
		LOGGER.info("Please enter the order total price");
		Double totalPrice = utils.getDouble();
		LOGGER.info("Please enter whether the order has been completed");
		Boolean orderStatus = utils.getBoolean();
		Orders orders = ordersDAO.create(new Orders(customerID, productID, productQty, totalPrice, orderStatus));
		LOGGER.info("Order created");
		return orders;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Orders update() {
		LOGGER.info("Please enter the ID of the order you would like to update");
		Long orderID = utils.getLong();
		LOGGER.info("Please enter the customer ID");
		Long customerID = utils.getLong();
		LOGGER.info("Please enter the product ID");
		Long productID = utils.getLong();
		LOGGER.info("Please enter the number of albums ordered");
		Double productQty = utils.getDouble();
		LOGGER.info("Please enter the order total price");
		Double totalPrice = utils.getDouble();
		LOGGER.info("Please enter whether the order has been completed");
		Boolean orderStatus = utils.getBoolean();
		Orders orders = ordersDAO.update(new Orders(orderID, customerID, productID, productQty, totalPrice, orderStatus));
		LOGGER.info("Order updated");
		return orders;
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
