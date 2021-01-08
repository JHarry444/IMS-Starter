package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class ItemController implements CrudController<Item> {

	public static final Logger LOGGER = LogManager.getLogger();

	private ItemDAO itemDAO;
	private Utils utils;

	public ItemController(ItemDAO itemDAO, Utils utils) {
		super();
		this.itemDAO = itemDAO;
		this.utils = utils;
	}

	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Item> readAll() {
		List<Item> items = itemDAO.readAll();
		for (Item item : items) {
			LOGGER.info(items.toString());
		}
		return items;
	}

	/**
	 * Creates a customer by taking in user input
	 * @param itemPrice 
	 * @param itemName 
	 * @param itemID 
	 */
	
	@Override
	public Item create(Object itemPrice, Class<Double> itemName, String itemID) {
		LOGGER.info("Please enter a first name");
		String firstName = utils.getString();
		LOGGER.info("Please enter a surname");
		String surname = utils.getString();
		Long item_name;
		String item_price;
		Item item = itemDAO.create(new Item(item_name, item_price, itemPrice));
		LOGGER.info("Item created");
		return item;
	}

	/**
	 * Updates an existing customer by taking in user input
	 * @param itemPrice 
	 */
	
	@Override
	public Item update(Class<Double> itemPrice) {
		LOGGER.info("Please enter the id of the customer you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter a first name");
		String firstName = utils.getString();
		LOGGER.info("Please enter a surname");
		String surname = utils.getString();
		Item item = itemDAO.update(new Item(id, itemPrice, surname));
		LOGGER.info("Item Updated");
		return item;
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the customer you would like to delete");
		Long id = utils.getLong();
		return itemDAO.delete(id);
	}

	@Override
	public Item create() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item create(Object itemPrice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item update() {
		// TODO Auto-generated method stub
		return null;
	}

}
