package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

/**
 * 
 * @author UsamaMahmood
 * 
 * 
 * Takes in Item details for CRUD functionality 
 */

public class ItemController implements CrudController<Item> {
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	private ItemDAO itemDAO;
	private Utils utils;
	
	public ItemController(ItemDAO itemDAO, Utils utils) {
		this.itemDAO = itemDAO;
		this.utils = utils;
	}
	
	/**
	 * Reads all items to the logger 
	 * 
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
	 * Creates an Item by taking in user input 
	 */

	@Override
	public Item create() {
		LOGGER.info("Please enter Item name");
		String item_name = utils.getString();
		LOGGER.info("Enter Item price");
		Double item_price = utils.getDouble();
		Item item = itemDAO.create(new Item(item_name, item_price));
		LOGGER.info("Item Created");
		return item;
	}
	
	/**
	 * Updates an existing Item by taking in user input
	 */

	@Override
	public Item update() {
		LOGGER.info("Please Endter the ID of the item you would like to update");
		Long item_id = utils.getLong();
		LOGGER.info("Please enter Item name");
		String item_name = utils.getString();
		LOGGER.info("Enter Item price");
		Double item_price = utils.getDouble();
		Item item = itemDAO.update(new Item(item_id, item_name, item_price));
		LOGGER.info("Item Updated");
		return item;
	}
	
	/**
	 * Deletes an existing item by the id of the item 
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