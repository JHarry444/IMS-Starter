package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

public class ItemController implements CrudController<Item>{
	
	public static final Logger LOGGER = LogManager.getLogger();

	private ItemDAO itemDAO;
	private Utils utils;


	public ItemController(ItemDAO itemDAO, Utils utils) {
		super();
		this.itemDAO = itemDAO;
		this.utils = utils;
	}


	/**
	 * Reads all items to the logger
	 */
	@Override
	public List<Item> readAll() {
		List<Item> items = itemDAO.readAll();
		for (Item item : items) {
			LOGGER.info(item.toString());
		}
		return items;
	}

	/**
	 * Creates an item by taking in user input
	 */
	@Override
	public Item create() {
		LOGGER.info("Please enter an item name");
		String item_name = utils.getString();
		LOGGER.info("Please enter a price for item");
		Float price = utils.getFloat();
		LOGGER.info("Please enter a quantity");
		Long quantity = utils.getLong();
		Item customer = itemDAO.create(new Item(item_name, price, quantity));
		LOGGER.info("Customer created");
		return customer;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Item update() {
		LOGGER.info("Please enter the item_id of the item you would like to update");
		Long item_id = utils.getLong();
		LOGGER.info("Please enter item name");
		String item_name = utils.getString();
		LOGGER.info("Please enter a price");
		Float price = utils.getFloat();
		LOGGER.info("Please enter a quantity");
		Long quantity = utils.getLong();
		Item item = itemDAO.update(new Item(item_id, item_name, price, quantity));
		LOGGER.info("Customer Updated");
		return item;
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the item you would like to delete");
		Long item_id = utils.getLong();
		return itemDAO.delete(item_id);
	}

	
}
