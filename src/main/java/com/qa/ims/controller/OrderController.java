package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.dao.OrderlineDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.Orderline;
import com.qa.ims.utils.Utils;

import java.util.Map;
import java.util.HashMap;

public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private OrderlineDAO orderlineDAO;
	private Utils utils;

	public OrderController(OrderDAO orderDAO, OrderlineDAO orderlineDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.orderlineDAO = orderlineDAO;
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

		Map<Long, Long> itemmap = new HashMap();

		LOGGER.info("Would you like to add an item? (Yes or no)");
		String answer = utils.getString();

		while (answer.toLowerCase().equals("yes")) {

			LOGGER.info("Please enter the Item ID for the order");
			Long itemid = utils.getLong();
			LOGGER.info("Please enter the quantity of the item");
			Long itemquant = utils.getLong();
			itemmap.put(itemid, itemquant);
			LOGGER.info("Would you like to add another item?(Yes or no)");
			answer = utils.getString();
		}

		Order order = orderDAO.create(new Order(id));
		Long orderid = order.getOrderid();
		System.out.println(orderid);
		for (Long itemid : itemmap.keySet()) {
			Long itemquant = itemmap.get(itemid);
			orderlineDAO.create(new Orderline(orderid, itemid, itemquant));
		}

		LOGGER.info("Order Created");

		return order;

	}

	@Override
	public Order update() {
		LOGGER.info("Please enter the ID for the order you wish to update");
		Long orderid = utils.getLong();

		Map<Long, Long> itemmap = new HashMap();

		LOGGER.info("Would you like to add an item? (Yes or no)");
		String answer = utils.getString();

		while (answer.toLowerCase().equals("yes")) {

			LOGGER.info("Please enter the Item ID for the order");
			Long itemid = utils.getLong();
			LOGGER.info("Please enter the quantity of the item");
			Long itemquant = utils.getLong();
			itemmap.put(itemid, itemquant);
			LOGGER.info("Would you like to add another item?(Yes or no)");
			answer = utils.getString();
		}

		Order order = orderDAO.create(new Order(orderid));
		Long orderlineid = order.getOrderlineid();
	
		for (Long itemid : itemmap.keySet()) {
			Long itemquant = itemmap.get(itemid);
			orderlineDAO.create(new Orderline(orderid, itemid, itemquant));
		}

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
