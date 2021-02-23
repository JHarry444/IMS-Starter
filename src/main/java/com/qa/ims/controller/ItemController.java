package com.qa.ims.controller;

import java.text.DecimalFormat;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

/**
 * Takes in item details for CRUD functionality
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
	 * Reads all items to the logger
	 */
	@Override
	public List<Item> readAll() {
		List<Item> items = itemDAO.readAll();
		if (items.isEmpty()) {
			LOGGER.info("There are no items in the database.");
		} else {
			for (Item item : items) {
				LOGGER.info(item.formattedString());
			}
		}	
		return items;
	}

	/**
	 * Creates a item by taking in user input
	 */
	@Override
	public Item create() {
		LOGGER.info("Please enter the item's name:");
		String name = utils.getString();
		LOGGER.info("Please enter the item's price:");
		double price = utils.getDouble();
		Item item = itemDAO.create(new Item(name, price));
		LOGGER.info(String.format("Item %s created, for %s%nThis item's ID is: %d", item.getName(), item.getFormattedPrice(), item.getId()));
		return item;
	}

	/**
	 * Updates an existing item by taking in user input
	 */
	@Override
	public Item update() {
		LOGGER.info("Please enter the ID of the item you would like to update:");
		Long id = utils.getLong();
		LOGGER.info("Please enter the item's new name:");
		String name = utils.getString();
		LOGGER.info("Please enter the item's new price:");
		double price = utils.getDouble();
		Item item = itemDAO.update(new Item(id, name, price));
		LOGGER.info(String.format("Item %s updated, for %s%nThis item's ID is: %d", item.getName(), item.getFormattedPrice(), item.getId()));
		return item;
	}

	/**
	 * Deletes an existing item by the id of the item
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the ID of the item you would like to delete:");
		Long id = utils.getLong();
		Item item = itemDAO.read(id);
		LOGGER.info(String.format("Are you sure you want to delete %s?%nY for Yes, N for No", item.getName()));
		String input = utils.getString();
		if (input.strip().equalsIgnoreCase("Y")) {
			LOGGER.info("Item deleted.");
			return itemDAO.delete(id);
		} else {
			LOGGER.info("Deletion canceled.");
		}
		return 0;
	}

}
