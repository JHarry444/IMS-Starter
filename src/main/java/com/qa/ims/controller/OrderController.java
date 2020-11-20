package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private CustomerDAO customerDAO;
	private ItemDAO itemDAO;
	private Utils utils;

	public OrderController(OrderDAO orderDAO, Utils utils, CustomerDAO customerDAO, ItemDAO itemDAO) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
		this.customerDAO = customerDAO;
		this.itemDAO= itemDAO;
	}

	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for (Order order : orders) {
			LOGGER.info(order.toString());
		}
		return orders;
	}

	@Override
	public Order create() {
		List<Customer> customers = customerDAO.readAll();
		for (Customer customer : customers) {
			LOGGER.info(customer.toString());
		}
		LOGGER.info("Please enter the customer id");
		long customerID = utils.getLong();
		Order order = orderDAO.create(new Order(customerID));
		String response;
		do {
			List<Item> items = itemDAO.readAll();
			for (Item item : items) {
				LOGGER.info(item.toString());
			}
			LOGGER.info("Please enter the item id of the chosen product");
			long itemID = utils.getLong();
			orderDAO.createLine(order.getId(), itemID);
			LOGGER.info("Would you like to add another?");
			response = utils.getString();
		} while (response.toLowerCase().equals("yes"));
		order = orderDAO.readLatest();
		return order;
	}

	@Override
	public Order update() {
		Long id = null;
		Order current = null;
		do {
			LOGGER.info("Please enter the order of the item you would like to update");
			id = utils.getLong();
			current = orderDAO.readOrder(id);
		} while (current == null);
		boolean exit = false;
		do {
			current = orderDAO.readOrder(id);
			LOGGER.info(current.toString());
			LOGGER.info("Add or delete an item? exit to stop");
			String selection = utils.getString().toLowerCase();
			Long itemID;
			switch (selection) {
			case "delete":
				LOGGER.info("Enter item id to delete");
				itemID = utils.getLong();
				orderDAO.deleteLine(current.getId(), itemID);
				break;
			case "add":
				List<Item> items = itemDAO.readAll();
				for (Item item : items) {
					LOGGER.info(item.toString());
				}
				LOGGER.info("Please enter the item id to add");
				itemID = utils.getLong();
				orderDAO.createLine(id, itemID);
				break;
			case "exit":
				exit = true;
				break;
			default:
				LOGGER.info("Invalid selection");
				break;
			}
		} while (!exit);

		return orderDAO.readOrder(id);
	}

	@Override
	public int delete() {
		for (Order order : readAll()) {
			LOGGER.info(order.toString());
		}
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = utils.getLong();
		orderDAO.deleteOrderLines(id);
		return orderDAO.delete(id);
	}

}