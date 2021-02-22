package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order>  {

	public static final Logger LOGGER = LogManager.getLogger();
	
	private OrderDAO orderDAO;
	private Utils utils;
	
	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}
	
	
	
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order.toString());
		}
		return orders;
	}

	
	@Override
	public Order create() {
		LOGGER.info("Please enter the Customer ID for the order");
		Long id = utils.getLong();
		LOGGER.info("Please enter the Order Line ID for the order");
		Long orderlineid = utils.getLong();
		Order order = orderDAO.create(new Order(id, orderlineid));
		LOGGER.info("Order Created");
		return order;
		}

	@Override
	public Order update() {
		LOGGER.info("Please enter the order id of the order you would like to update");
		Long orderid = utils.getLong();
		LOGGER.info("Please enter an item id");
		Long id = utils.getLong();
		LOGGER.info("Please enter an Order Line ID");
		Long orderlineid  = utils.getLong();
		Order order = orderDAO.update(new Order(orderid, id, orderlineid));
		LOGGER.info("Order Updated");
		return order;
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long orderid = utils.getLong();
		return orderDAO.delete(orderid);
	}

}
