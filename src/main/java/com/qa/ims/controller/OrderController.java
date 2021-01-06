package com.qa.ims.controller;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order>{
	
	public static final Logger LOGGER = LogManager.getLogger();

	
	private OrderDAO orderDAO;
	private ItemDAO itemDAO;
	private Utils utils;

	public OrderController(OrderDAO orderDAO, Utils utils, ItemDAO itemDAO) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
		this.itemDAO = itemDAO;
	}

	/**
	 * Creates an item by taking in user input
	 */
	@Override
	public Order create() {
		LOGGER.info("Please enter a customer_id");
		Long customer_id = utils.getLong();
		Order order = orderDAO.create(new Order(customer_id));
		LOGGER.info("Order created");
		LOGGER.info("Would you like to add another item to your basket? Yes or no:");
		String newItem = utils.getString();
		addItem(newItem);
		return order;
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 * 
	 * @return
	 */
	
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long order_item_id = utils.getLong();
		return orderDAO.delete(order_item_id);
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	
	@Override
	public Order update() {
	LOGGER.info("Please enter the order_id of the item you would like to update");
		Long order_id = utils.getLong();
	LOGGER.info("Please enter new customer_id");
		Long customer_id = utils.getLong(); 
		Order order = new Order(order_id, customer_id);
		return order;
	}
	
	public List<Item> addItem(String newItem) {
		if (newItem.toLowerCase().equals("yes")) {
			List<Item> items = itemDAO.readAll();
			for(Item item : items) {
				LOGGER.info(item.toString());
			}
			
			return items ;
			
		}else {
			
			return null;
			
		}
	}

	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order.toString());
		}
		return orders;
	}

}