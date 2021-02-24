package com.qa.ims.controller;

import java.text.DecimalFormat;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.dao.OrderDetailDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderDetail;
import com.qa.ims.utils.Utils;

/**
 * Takes in order details for CRUD functionality
 *
 */
public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = LogManager.getLogger();
	private DecimalFormat decim = new DecimalFormat("0.00");

	private OrderDAO orderDAO;
    private OrderDetailDAO orderDetailDAO;
	private Utils utils;

	public OrderController(OrderDAO orderDAO, OrderDetailDAO orderDetailDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
        this.orderDetailDAO = orderDetailDAO;
		this.utils = utils;
	}

	/**
	 * Reads all orders to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		if (orders.isEmpty()) {
			LOGGER.info("There are no orders in the database.");
		} else {
			Double totalCost;
			for (Order order : orders) {
				totalCost = 0d;
				LOGGER.info(order.formattedString());
				for (OrderDetail orderDetail : orderDetailDAO.readOrder(order.getId())) {
					LOGGER.info(orderDetail.formattedString());
					totalCost += orderDetail.getPrice();
				}
				LOGGER.info(String.format("Total Cost for Order %s: %s%n", order.getId(), decim.format(totalCost)));
			}
		}
		return orders;
	}

	/**
	 * Creates a order by taking in user input
	 */
	@Override
	public Order create() {
		Long custID;
		Order order = null;
		while (order == null) {
			LOGGER.info("Please enter a customer ID:");
			custID = utils.getLong();
        	order = orderDAO.create(new Order(custID));
			if (order == null) {
				LOGGER.info("Customer doesn't exist");
			}
		}
		LOGGER.info("Please enter a list of items IDs to add to the order, with their quantity\nEnter ! to stop");
        Long itemID = utils.getLong();
		Long quantity;
        while (!itemID.equals(-1l)) {
			LOGGER.info("Please enter the quantity:");
            quantity = utils.getLong();
			if (quantity.equals(-1l)) { 
				break; 
			}
			if (orderDetailDAO.readOrderItem(order.getId(), itemID) == null) {
				orderDetailDAO.create(new OrderDetail(order.getId(), itemID, quantity));
				LOGGER.info("Item added, enter next ID:");
			} else {
				LOGGER.info("This item already exists in this order, use update to change it\nEnter new ID:");
			}
            itemID = utils.getLong();
        }
		return order;
	}

	/**
	 * Updates an existing order by taking in user input
	 */
	@Override
	public Order update() {
		LOGGER.info("Please enter the id of the order you would like to update:");
		Long id = utils.getLong();
		LOGGER.info("Please enter a new customer ID:");
		Long custID = utils.getLong();
		Order order = orderDAO.update(new Order(id, custID));
		LOGGER.info("Please enter a list of items IDs to add to the order, with their quantity\nIf the item is already in the order, it will be deleted\nEnter ! to stop");
        Long itemID = utils.getLong();
		while (!itemID.equals(-1l)) {
			LOGGER.info("Please enter the quantity:");
			Long quantity = utils.getLong();	
			if (quantity.equals(-1l)) { 
				break; 
			}
            orderDetailDAO.update(new OrderDetail(order.getId(), itemID, quantity));
		    LOGGER.info("Item added, enter next ID:");
            itemID = utils.getLong();
        }
		return order;
	}

	/**
	 * Deletes an existing order by the id of the order
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order you would like to delete items from:");
		Long orderID = utils.getLong();
		if (orderDetailDAO.readOrder(orderID).isEmpty()) {
			LOGGER.info("This order doesn't contain any items, it will be deleted.\nTo add items to this order, re-create it.");
			return orderDAO.delete(orderID);
		}
		LOGGER.info("Please enter the item ids you would like to delete from the order. Deleting all items will delete the order. Entering * will delete all items\nEnter ! to stop");
		Long itemID = utils.getLong();
		while (!itemID.equals(-1l)) {
			if (itemID.equals(-2l)) {
				LOGGER.info(String.format("Are you sure you want to delete all items in order %d? This will delete all items in the order%nY for Yes, N for No", orderID));
				String input = utils.getString();
				if (input.strip().equalsIgnoreCase("Y")) {		
					LOGGER.info("Deleting all items:");
					for (OrderDetail orderDetail : orderDetailDAO.readOrder(orderID)) {
						orderDetailDAO.delete(orderDetail.getID());
					}
					return orderDAO.delete(orderID);
				}
			}
			orderDetailDAO.delete(orderDetailDAO.readOrderItem(orderID, itemID).getID());
			LOGGER.info("Item deleted");
			if (orderDetailDAO.readOrder(orderID).isEmpty()) {
				LOGGER.info("All items deleted from order, order deleted");
				return orderDAO.delete(orderID);
			}
			LOGGER.info("Enter next id:");
			itemID = utils.getLong();
		}
		return 0;
	}

}
