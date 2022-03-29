package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

// Similar to the customer controller, this will take in details on the items for CRUD functionality.
public class ItemController implements CrudController<Item> {
    // This is the logger, an instance must be created and this is the simplest way.
	public static final Logger LOGGER = LogManager.getLogger();

	private ItemDAO itemDAO;
	private Utils utils;
	
	public ItemController(ItemDAO itemDAO, Utils utils) {
		super();
		this.itemDAO = itemDAO;
		this.utils = utils;
	}
//  This will read all of the items in the list 'Item'.
	@Override
	public List<Item> readAll() {
		List<Item> items = itemDAO.readAll();
		for (Item item : items) {
			LOGGER.info(item);
		}
		return items;
	}
//  This will create an item based on the user's input to the questions asked.
	@Override
	public Item create() {
		LOGGER.info("Please enter an item name");
		String name = utils.getString();
		LOGGER.info("Please write a description of the item");
		String description = utils.getString();
		LOGGER.info("Please enter a price for the item");
		double price = utils.getDouble();
		Item item = itemDAO.create(new Item(name, description, price));
		LOGGER.info("Item created");
		return item;
	}
//  This will give the user the ability to change any information of a given item using its id.
	@Override
	public Item update() {
		LOGGER.info("Please enter the id of the item you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter the name of the item");
		String name = utils.getString();
		LOGGER.info("Please enter a description for the new item");
		String description = utils.getString();
		LOGGER.info("Please enter a price for the item");
		double price = utils.getDouble();
		Item item = itemDAO.update(new Item(id, name, description, price));
		return item;
	}
//  This will allow a user to delete an item with the matching id they enter.
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the item you would like to delete");
		Long id = utils.getLong();
		return itemDAO.delete(id);
	}

}
