package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderItemsDAO;

import com.qa.ims.persistence.domain.OrderItems;
import com.qa.ims.utils.Utils;

public class OrderItemsController implements CrudController<OrderItems> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderItemsDAO orderItemsDAO;
	private Utils utils;

	public OrderItemsController(OrderItemsDAO orderItemsDAO, Utils utils) {
		super();
		this.orderItemsDAO = orderItemsDAO;
		this.utils = utils;
	}

	@Override
	public List<OrderItems> readAll() {
		LOGGER.info("Would you like to read ALL orders or by an ID?");
		String choice = utils.getString();
		
		if (choice.equalsIgnoreCase("ALL")) {
		List<OrderItems> orderItems = orderItemsDAO.readAll();
		for (OrderItems orderItem : orderItems) {
			LOGGER.info(orderItem);
		}
		LOGGER.info(calculateCost(orderItems));
		return orderItems;
		}
		else if (choice.equalsIgnoreCase("ID")){
			LOGGER.info("Enter ID of order");
			Long id = utils.getLong();
			List<OrderItems> orderItemsSpecific = orderItemsDAO.readSpecific(id);
			for(OrderItems orderItem : orderItemsSpecific) {
				LOGGER.info(orderItem);
			}
			LOGGER.info(calculateCost(orderItemsSpecific));
			
			return orderItemsSpecific;
		}
		return null;
	}

	public OrderItems create(Long cust_id, Long order_id) {
		Long item_id = 0L;
		Long quantity = 0L;

		LOGGER.info("Please enter the item id");
		item_id = utils.getLong();

		LOGGER.info("Please enter the quantity");
		quantity = utils.getLong();

		LOGGER.info("Test before");
		OrderItems orderItem = orderItemsDAO.create(new OrderItems(item_id, order_id, quantity));
		LOGGER.info("Test after");
		return orderItem;
	}

	@Override
	public OrderItems update() {
		LOGGER.info("");
		return null;
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order you want to delete");
		Long order_id = utils.getLong();
		return orderItemsDAO.delete(order_id);
	}

	@Override
	public OrderItems create() {
		return null;
	}

	public Double calculateCost(List<OrderItems> oi) {
		LOGGER.info("Total Cost of orders: ");
		return orderItemsDAO.calculateCost(oi);
	}

}
