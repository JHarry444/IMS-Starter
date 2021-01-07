package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderlineDAOTest;
import com.qa.ims.persistence.domain.Orderline;
import com.qa.ims.utils.Utils;

/**
 * Takes in item details for CRUD functionality
 *
 */
public class OrderlineController implements CrudController<Orderline> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderlineDAOTest orderlineDAO;
	private Utils utils;

	public OrderlineController(OrderlineDAOTest orderlineDAO, Utils utils) {
		super();
		this.orderlineDAO = orderlineDAO;
		this.utils = utils;
	}

	/**
	 * Reads all items to the logger
	 */
	@Override
	public List<Orderline> readAll() {
		List<Orderline> orderlines = orderlineDAO.readAll();
		for (Orderline orderline : orderlines) {
			LOGGER.info(orderline.toString());
		}
		return orderlines;
	}

	/**
	 * Creates an item by taking in user input
	 */
	@Override
	public Orderline create() {
		LOGGER.info("Please enter the order ID");
		Long orderID = utils.getLong();
		LOGGER.info("Please enter the product ID");
		Long productID = utils.getLong();
		Orderline orderline = orderlineDAO.create(new Orderline(orderID, productID));
		LOGGER.info("Orderline created");
		return orderline;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Orderline update() {
		LOGGER.info("Please enter the ID of the orderline you would like to update");
		Long orderlineID = utils.getLong();
		LOGGER.info("Please enter the order ID");
		Long orderID = utils.getLong();
		LOGGER.info("Please enter the product ID");
		Long productID = utils.getLong();
		Orderline orderline = orderlineDAO.create(new Orderline(orderlineID, orderID, productID));
		LOGGER.info("Orderline updated");
		return orderline;
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the ID of the orderline you would like to delete");
		Long orderlineID = utils.getLong();
		return orderlineDAO.delete(orderlineID);
	}

}