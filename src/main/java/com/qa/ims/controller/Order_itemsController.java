package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.Order_itemsDAO;
import com.qa.ims.persistence.domain.Order_items;
import com.qa.ims.utils.Utils;

public class Order_itemsController implements CrudController<Order_items> {

	public static final Logger LOGGER = LogManager.getLogger();

	private Order_itemsDAO order_itemsDAO;
	private Utils utils;

	public Order_itemsController(Order_itemsDAO order_itemsDAO, Utils utils) {
		super();
		this.order_itemsDAO = order_itemsDAO;
		this.utils = utils;
	}

	@Override
	public Order_items create() {
		LOGGER.info("Please enter an item_id");
		Long item_id = utils.getLong();
		LOGGER.info("Please enter the quantity");
		int quantity = utils.getInt();
		utils.getString();
//		LOGGER.info("Please enter the order_id");
//		Long order_id = utils.getLong();
		
		Order_items order_items = order_itemsDAO.create(new Order_items(item_id, quantity)); 
		LOGGER.info("Order created");
		return order_items;
		
	}

	@Override
	public Order_items update() {
		LOGGER.info("Please enter an item_id you want to update");
		Long item_id = utils.getLong();
		LOGGER.info("Please enter the new quantity");
		int quantity = utils.getInt();
		LOGGER.info("Please enter the order_id");
		Long order_id = utils.getLong();
		Order_items order_items = order_itemsDAO.create(new Order_items(item_id, quantity, order_id));
		LOGGER.info("Order created");
		return order_items;
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter item_id that you would like to delete");
		Long item_id = utils.getLong();
		return order_itemsDAO.delete(item_id);
	}

	@Override
	public List<Order_items> readAll() {
		List<Order_items> order_items = order_itemsDAO.readAll();
		for (Order_items order_items1 : order_items) {
			LOGGER.info(order_items1.toString());
		}
		return order_items;

	}
}
