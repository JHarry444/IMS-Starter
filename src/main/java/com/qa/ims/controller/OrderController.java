package com.qa.ims.controller;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	/**
	 * Creates an item by taking in user input
	 */
	@Override
	public Order create() {
		LOGGER.info("Please enter a customer_id");
		Long customer_id = utils.getLong();
		LOGGER.info("Please enter a product_id");
		Long product_id = utils.getLong();
		LOGGER.info("Please enter the amount you would like");
		Long quantity = utils.getLong();
		Order order = orderDAO.create(new Order(customer_id, product_id, quantity, null));
		LOGGER.info("Order created");
		return order;
	}
	

	/**
	 * Updates an existing customer by taking in user input
	 */
//	@Override
//	public Order update() {
//		LOGGER.info("Please enter the item_id of the item you would like to update");
//		Long item_id = utils.getLong();
//		LOGGER.info("Please enter item name");
//		String item_name = utils.getString();
//		LOGGER.info("Please enter a price");
//		Long price = utils.getLong();
//		LOGGER.info("Please enter a quantity");
//		Long quantity = utils.getLong();
//		Item item = itemDAO.update(new Item(item_id, item_name, price, quantity));
//		LOGGER.info("Customer Updated");
//		return item;
//	}

	/**
	 * Deletes an existing customer by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long order_id = utils.getLong();
		return orderDAO.delete(order_id);
	}
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order.toString());
		}
		return null;
	}
	@Override
	public Order update() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
