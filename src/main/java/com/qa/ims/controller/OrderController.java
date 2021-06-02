package com.qa.ims.controller;

import java.time.LocalDate;
import java.util.ArrayList;

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
		LOGGER.info("Please enter the id of the customer making the order");
		Long customerId = utils.getLong();
		LOGGER.info("Please enter the total amount (ex. \"19.99\")");
		Double total = utils.getDouble();
		LOGGER.info("Please enter the date the order was placed *MUST BE IN \"YYYY-MM-DD\" FORMAT*");
		String date = utils.getString();
		String[] dateArr = date.split("-", 0);
//		PUT EXCEPTION HANDLERS HERE!!!!!!!!!
		int year = Integer.parseInt(dateArr[0]);
		int month = Integer.parseInt(dateArr[1]);
		int day = Integer.parseInt(dateArr[2]);
		Order order = orderDAO.create(new Order(customerId, total, LocalDate.of(year, month, day)));
		LOGGER.info("Customer created");
		return order;
	}
	
	public Order read() {
		LOGGER.info("Please enter the id of the item you'd like to read");
		Long id = utils.getLong();
		Order order = orderDAO.read(id);
		LOGGER.info(order);
		return order;
	}
	
	@Override
	public ArrayList<Order> readAll() {
		ArrayList<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}
	
	@Override
	public Order update() {
		LOGGER.info("Please enter the id of the order you'd like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter the id of the customer making the order");
		Long customerId = utils.getLong();
		LOGGER.info("Please enter the total amount (ex. \"19.99\")");
		Double total = utils.getDouble();
		LOGGER.info("Please enter the date the order was placed (ex. \"YYYY-MM-DD\")");
		String date = utils.getString();
		String[] dateArr = date.split("-", 0);
//		PUT EXCEPTION HANDLERS HERE!!!!!!!!!
		int year = Integer.parseInt(dateArr[0]);
		int month = Integer.parseInt(dateArr[1]);
		int day = Integer.parseInt(dateArr[2]);
		Order order = new Order(id, customerId, total, LocalDate.of(year, month, day));
		Order updated = orderDAO.update(order);
		LOGGER.info("Item Updated");
		return updated;
	}
	
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the item you would like to delete");
		Long id = utils.getLong();
		return orderDAO.delete(id);
	}

}
