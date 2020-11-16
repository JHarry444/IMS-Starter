package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.Order_ItemDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order>{
	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private Utils utils;
	private Order_ItemDAO order_itemDAO;
	private OrderAction orderaction;
	
	public OrderController(OrderDAO orderDAO, Utils utils, Order_ItemDAO order_itemDAO) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
		this.order_itemDAO = order_itemDAO;
		this.orderaction = orderaction;
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
		Order order = orderDAO.create(new Order(custID));
		LOGGER.info("please enter Item ID to add to order");
		Long itemID = utils.getLong();
		order_itemDAO.create(orderDAO.readLatest().getID(),itemID);
		boolean active = true;
		while(active) {
			LOGGER.info("Add another Item? Y/N");
			String result = utils.getString();
			if(result.toLowerCase().equals("y")) {
				LOGGER.info("Please enter the ID of the item to add to the order");
				itemID = utils.getLong();
				order_itemDAO.create(orderDAO.readLatest().getID(),itemID);
			}
			else if (result.toLowerCase().equals("n")) {
				active = false;
			}
		}
		return order;	
		}
	
	@Override
	public Order update() {
		LOGGER.info("Please enter the ID of the order to update");
		Long orderID  =utils.getLong();
		boolean on = true;
		while(on) {
			Order order = orderDAO.readSingle(orderID);
			LOGGER.info(order.toString()+"\r");
			LOGGER.info("What would you like to do to order "+orderID+"?");
			OrderAction.printActions();
			OrderAction orderaction = OrderAction.getAction(utils);
			switch (orderaction) {
			case ADD:
				LOGGER.info("Please enter the ID of the item to add");
				Long itemID = utils.getLong();
				order_itemDAO.create(orderID, itemID);
				break;
			case REMOVE:
				LOGGER.info("Please enter the ID of the item to remove");
				itemID = utils.getLong();
				order_itemDAO.remove(orderID, itemID);
				break;
			case CUSTOMER:
				LOGGER.info("Please enter the new customer ID");
				Long customerID = utils.getLong();
				order_itemDAO.cust(orderID, customerID);
				break;
			case RETURN:
				on = false;
				break;
			}
		}	
		return null;
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter the ID of the order to delete:");
		Long ID= utils.getLong();
		return orderDAO.delete(ID);
	}
	

}
