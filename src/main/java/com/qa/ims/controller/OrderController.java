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
		LOGGER.info("Please enter an order_id");
		Long order_id = utils.getLong();
		LOGGER.info("Please enter a customer_id");
		Long cust_id = utils.getLong();
		Order order = orderDAO.create(new Order(order_id, cust_id));
		LOGGER.info("Order created");
		return order;
	}

	@Override
	public List<Order> readAll() {
		List<Order> order = orderDAO.readAll();
		for (Order order1 : order) {
			LOGGER.info(order.toString());
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
		return null;
	}

}
