package com.qa.ims.controller;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;

import com.qa.ims.persistence.domain.Order;

import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private Utils utils;

	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}

	@Override
	public Order create() {
		LOGGER.info("Please enter a customer id");
		int cust_id = utils.getInt();
		LOGGER.info("Please enter an item id");
		Long item_id = utils.getLong();
		LOGGER.info("Please enter quantity");
		int quantity = utils.getInt();
		Order order = orderDAO.create(new Order(cust_id, item_id, quantity));
		LOGGER.info("Order created");
		return order;
	}

	@Override
	public List<Order> readAll() {
		List<Order> order = orderDAO.readAll();
		for (Order order1 : order) {
			LOGGER.info(order1.toString());
		}
		return order;
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter the order_id of the order you would like to delete");
		Long order_id = utils.getLong();
		return orderDAO.delete(order_id);
	}

	@Override
	public Order update() {
	//	LOGGER.info("Please enter a customer id");
	//	int cust_id = utils.getInt();
		LOGGER.info("Please enter the id of the item you would like to update");
		Long item_id = utils.getLong();
		LOGGER.info("Please enter new quantity");
		int quantity = utils.getInt();
		Order order = orderDAO.create(new Order(item_id, quantity));
		LOGGER.info("Order updated");
		return order;
	}

}
