package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrdersDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.Utils;

public class OrdersController implements CrudController<Orders> {
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	private OrdersDAO ordersDAO;
	private Utils utils;

	private ItemDAO itemController;
	
	
	public OrdersController(OrdersDAO ordersDAO, Utils utils, ItemDAO itemController) {
		super();
		this.ordersDAO = ordersDAO;
		this.utils = utils;
		this.itemController = itemController;
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
		LOGGER.info("Enter your customer ID");
		Long id = utils.getLong();
		Orders order = ordersDAO.create(new Orders(new Customer(id, null, null)));
		List<Item> items = itemController.readAll();
		String result;
		do {
			for(Item item: items) {
				LOGGER.info("Item ID: " + item.getItem_id() + " Name: " + item.getItem_name());	
			}
			LOGGER.info("Enter Item ID");
			Long item_id = utils.getLong();
			order.setItem(new Item(item_id, null, null));
			ordersDAO.createOrdersItems(order);
			LOGGER.info("Item " + item_id + " Added");
			LOGGER.info("Would you like to add another?");
			result = utils.getString();
		} while(!result.toLowerCase().equals("yes"));

		return order;	
	}
	
	// might not need this 	
	@Override
	public Orders update() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the Order you would like to delete");
		Long order_id = utils.getLong();
		return ordersDAO.delete(order_id);
	} 

}
