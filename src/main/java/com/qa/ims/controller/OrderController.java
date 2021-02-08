package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;

import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {
	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private Utils utils = new Utils();

	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}

	@Override
	public List<Order> readAll() {
//		List<Order> orders = orderDAO.readAll();
//		for (Order order : orders) {
//			LOGGER.info(order.toString());
//		}
//		
//		LOGGER.info("Would you like to read ALL orders or by an ID?");
//		String choice = utils.getString();
//		
//		if (choice.equalsIgnoreCase("ALL")) {
//		List<Order> orderItems = orderDAO.readAllItems();
//		for (Order order : orderItems) {
//			LOGGER.info(order.itemsToString());
//		}
//		LOGGER.info(calculateCost(orderItems));
//		return orderItems;
//		}
//		else if (choice.equalsIgnoreCase("ID")){
//			readSpecific();
//		}

		List<Order> orders = orderDAO.readAllTwo(false);
		for (Order order : orders) {
			LOGGER.info(order.toString());
		}
		LOGGER.info("Would you like to read ALL orders or by an ID?");
		String choice = utils.getString();

		if (choice.equalsIgnoreCase("ALL")) {
			List<Order> orderItems = orderDAO.readAllTwo(true);
			for (Order orderItem : orderItems) {
				LOGGER.info(orderItem.itemsToString());
			}
			//LOGGER.info(calculateCost(orderItems));
			return orderItems;
		} else if (choice.equalsIgnoreCase("ID")) {
			readSpecific();
		}
		return orders;
	}

	@Override
	public Order create() {
		boolean complete = false;
		String choice;
		LOGGER.info("Enter your customer id");
		Long cust_id = utils.getLong();
		Order order = orderDAO.createUpdateOrder(new Order(cust_id),true);
		do {
			LOGGER.info("Would you like to order another item? Y/N");
			choice = utils.getString();
			if (choice.equalsIgnoreCase("Y")) {
				createOrderItem(order.getOrder_id());
			} else {
				complete = true;
			}
		} while (!complete);
		LOGGER.info("Order created");
		return order;
	}

	public Order createOrderItem(Long order_id) {
		Long item_id = 0L;
		Long quantity = 0L;

		LOGGER.info("Please enter the item id");
		item_id = utils.getLong();
		LOGGER.info("Please enter the quantity");
		quantity = utils.getLong();
		Order order = orderDAO.createUpdateItem(new Order(item_id, order_id, quantity), true);
		return order;
	}

	@Override
	public Order update() {
		LOGGER.info("Please enter your Order ID: ");
		Long order_id = utils.getLong();
		LOGGER.info("Please enter the Item ID: ");
		Long item_id = utils.getLong();
		LOGGER.info("Please enter the quantity: ");
		Long quantity = utils.getLong();
		Order order = orderDAO.createUpdateOrder(new Order(item_id, order_id, quantity), false);
		return null;
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order you want to delete");
		Long order_id = utils.getLong();
		LOGGER.info("Delete complete Order or individual item. Order | Item");
		String choice = utils.getString();
		if (choice.equalsIgnoreCase("Order")) {
			return orderDAO.delete(order_id);
		} else if (choice.equalsIgnoreCase("Item")) {
			deleteItem(order_id);
			return 0;
		} else {
			LOGGER.info("Incorrect selection. Please choose - Order | Item");
		}
		return 0;
	}

	public int deleteItem(Long order_id) {
		LOGGER.info("Enter the id of the item you want to delete");
		Long item_id = utils.getLong();
		return orderDAO.deleteOrderItem(order_id, item_id);
	}

	public Double calculateCost(List<Order> o) {
		LOGGER.info("\nTotal Cost of orders: £");
		return orderDAO.calculateCost(o);
	}

	public int deleteNullOrders() {
		return orderDAO.deleteNullOrders();
	}

	public List<Order> readSpecific() {
		LOGGER.info("Enter ID of order");
		Long id = utils.getLong();
		List<Order> orderSpecific = orderDAO.readSpecific(id);
		for (Order order : orderSpecific) {
			LOGGER.info(order.itemsToString());
		}
		LOGGER.info(calculateCost(orderSpecific));
		return orderSpecific;
	}
}