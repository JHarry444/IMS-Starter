package com.qa.ims.controller;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.util.List;

import com.qa.ims.persistence.domain.Order;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.utils.DBUtils;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private Utils utils;

	public OrderController(OrderDAO orderDAO, Utils utils) {
		this.orderDAO = orderDAO;
		this.utils = utils;
	}

	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}

	@Override
	public Order create() {
		LOGGER.info("Please enter a customer id");
		long customerID = utils.getLong();
		LOGGER.info("Please enter a item id");
		long itemID = utils.getLong();
		Order order = orderDAO.create(new Order(customerID, itemID));
		LOGGER.info("Order created");
		return order;
	}

	@Override
	public Order update() {
		LOGGER.info("Please enter an order id");
		long orderID = utils.getLong();
		LOGGER.info("Please enter an item id");
		long itemID = utils.getLong();
		LOGGER.info("Please enter a customer id");
		long customerID = utils.getLong();
		Order order = orderDAO.update(new Order(orderID, itemID, customerID));
		LOGGER.info("Item successfully added to order");
		return order;
	}

	@Override
	public int delete() {
		LOGGER.info("Delete item or order?");
		String deleteType = utils.getString().toUpperCase();
		if (deleteType.equals("ORDER")) {
			LOGGER.info("Please enter the id of the order you wish to delete");
			Long order_id = utils.getLong();
			LOGGER.info("Order with id " + order_id + " has been deleted");
			return orderDAO.delete(order_id);
		} else {
			LOGGER.info("Please enter the id of the order you wish to be edited");
			Long order_id = utils.getLong();
			LOGGER.info("Please enther the id of the item you wish to be removed");
			Long item_id = utils.getLong();
			LOGGER.info("Item with id " + item_id + " has been deleted from order:" + order_id);
			return orderDAO.deleteItem(order_id,item_id);
			
		}
	}

}
