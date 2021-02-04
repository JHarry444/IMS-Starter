package com.qa.ims.controller;

import com.qa.ims.persistence.domain.Item;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.utils.Utils;

public class ItemController implements CrudController<Item> {

	public static final Logger LOGGER = LogManager.getLogger();

	private ItemDAO itemDAO;
	private Utils utils;

	public ItemController(ItemDAO itemDAO, Utils utils) {
		this.itemDAO = itemDAO;
		this.utils = utils;
	}

	@Override
	public List<Item> readAll() {
		List<Item> items = itemDAO.readAll();
		for (Item item : items) {
			LOGGER.info(item);
		}
		return items;
	}

	@Override
	public Item create() {
		LOGGER.info("Please enter an item name");
		String itemName = utils.getString();
		LOGGER.info("Please enter an item price");
		int itemPrice = utils.getInt();
		utils.getString();
		Item item = itemDAO.create(new Item(itemName, itemPrice));
		LOGGER.info("Item created");
		return item;
	}

	@Override
	public Item update() {
		LOGGER.info("Please enter the id of the item you wish to update");
		Long item_id = utils.getLong();
		LOGGER.info("Please entere an item Name");
		String item_name = utils.getString();
		LOGGER.info("Please enter an item price");
		int item_price = utils.getInt();
		utils.getString();
		Item item = itemDAO.update(new Item(item_id, item_name,item_price));
		LOGGER.info("Item Updated");
		return item;
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the item you wish to delete");
		Long item_id = utils.getLong();
		LOGGER.info("Item with id " + item_id + " has been deleted");
		return itemDAO.delete(item_id);
	}

}
