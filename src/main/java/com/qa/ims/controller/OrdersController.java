package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrdersDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.Utils;

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
	 * Reads all Orders to the Logger 
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
	 * Order is created when items have been added.
	 * Then the Order has Items and Customers in it, along with their info 
	 */
	@Override
	public Orders create() {
		LOGGER.info("Enter your Customer ID");
		Long id = utils.getLong();
		Orders order = ordersDAO.create(new Orders(new Customer(id,null,null)));
		
		String result;
		do {
			LOGGER.info("Enter your Order ID");
			Long order_id = utils.getLong();
			LOGGER.info("Enter Item ID");
			int item_id = utils.getInt();
			LOGGER.info("Enter the Quantity");
			int quantity = utils.getInt();
		OrdersDAO.addItems(new Orders(order_id, null, item_id, null, quantity, null));
		} 
			
		}
		return order;
	}
	@Override
	public Orders update() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int delete() {
		// TODO Auto-generated method stub
		return 0;
	} 

}
