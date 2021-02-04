package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.dao.OrderItemsDAO;

import com.qa.ims.persistence.domain.Order;

import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {
	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private OrderItemsDAO orderItemsDAO = new OrderItemsDAO();
	private Utils utils = new Utils();
	private OrderItemsController orderItems = new OrderItemsController(orderItemsDAO, utils);

	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}

	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order);
		}
		//orderItemsDAO.readOrderItemsJoin();
		return orders;
	} 

	@Override
	public Order create() {
		boolean complete = false;
		
		String choice;
		
		LOGGER.info("Enter your customer id");
		Long cust_id = utils.getLong();
		Order order = orderDAO.create(new Order(cust_id));
		do {
			LOGGER.info("Would you like to order another item? Y/N");
			choice = utils.getString();
			if (choice.equalsIgnoreCase("Y")) {
				orderItems.create(cust_id, order.getOrder_id());
			} else {
				complete = true;
			}

		
		} while (!complete);
	
		LOGGER.info("Order created");
		return order;
	}

	@Override
	public Order update() {
		LOGGER.info("Please enter the order id");
		Long order_id = utils.getLong();
		LOGGER.info("Please enter the new customer id");
		Long cust_id = utils.getLong();
		Order order = orderDAO.update(new Order(order_id, cust_id));
		return order;		
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order you want to delete");
		Long order_id = utils.getLong();
		return orderDAO.delete(order_id);
	}
}
