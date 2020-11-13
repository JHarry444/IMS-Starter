package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order>{
	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private Utils utils;
	
	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}
	
// reads all orders	
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order:orders) {
			LOGGER.info(order.toString());
		}
		return orders;
	}

	@Override
	public Order create() {
		LOGGER.info("Please enter the ID of the customer making the order");
		Long custID = utils.getLong();
		LOGGER.info("please enter Item ID to add to order");
		Long itemID = utils.getLong();
		Order order = orderDAO.create(new Order(custID));
		return order;
	}
	public Order moreitems() {
		LOGGER.info("Type return to exit, or the ID of another item to add to the order");
		String ret = utils.getString();
		while(!ret.toLowerCase().equals("return")) {
			
		}
	}
	@Override
	public Order update() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete() {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
