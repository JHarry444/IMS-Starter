package com.qa.ims.controller;

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
		for (Order order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}

	/**
	 * Creates a order by taking in user input
	 */
	@Override
	public Order create() {
		LOGGER.info("Please enter a customer ID");
		Long custID = utils.getLong();
        Order order = orderDAO.create(new Order(custID));
		LOGGER.info("Please enter a list of items IDs to add to the order, with their quantity\nEnter ! to stop");
        Long itemID = utils.getLong();
        LOGGER.info("Please enter quantity");
        Double quantity = utils.getDouble();
        while (!itemID.equals((long) -1)) {
            orderDetailDAO.create(new OrderDetail(order.getId(), itemID, quantity));
		    LOGGER.info("Item added, enter next ID");
            itemID = utils.getLong();
            LOGGER.info("Please enter quantity");
            quantity = utils.getDouble();    
        }
		return order;
	}

	/**
	 * Updates an existing order by taking in user input
	 */
	@Override
	public Order update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter a new customer ID");
		Long custID = utils.getLong();
		Order order = orderDAO.update(new Order(id, custID));
		LOGGER.info("Please enter a list of items IDs to add to the order, with their quantity\nIf the item is already in the order, it will be deleted\nEnter ! to stop");
        Long itemID = utils.getLong();
        LOGGER.info("Please enter quantity");
        Double quantity = utils.getDouble();
		while (!itemID.equals((long) -1)) {
            orderDetailDAO.update(new OrderDetail(order.getId(), itemID, quantity));
		    LOGGER.info("Item added, enter next ID");
            itemID = utils.getLong();
            LOGGER.info("Please enter quantity");
            quantity = utils.getDouble();    
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
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = utils.getLong();
		for (OrderDetail orderDetail : orderDetailDAO.readOrder(id)) {
			orderDetailDAO.delete(orderDetail.getID());
		}
		return orderDAO.delete(id);
	}

}
