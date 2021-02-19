package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

public class ItemController implements CrudController<Item> {

	public static final Logger LOGGER = LogManager.getLogger();

	private ItemDAO itemDAO;
	private Utils utils;

	public ItemController(ItemDAO itemDAO, Utils utils) {
		super();
		this.itemDAO = itemDAO;
		this.utils = utils;
	}

	@Override
	public Item create() {
		LOGGER.info("Please enter a title");
		String title = utils.getString();
		LOGGER.info("Please enter a value");
		float value = utils.getfloat();
		Item item = itemDAO.create(new Item(title, value));
		LOGGER.info("Item created");
		return item;
	}

	@Override
	public Item update() {
		LOGGER.info("Please enter the id of the item you would like to update");
		Long item_id = utils.getLong();
		LOGGER.info("Please enter a title");
		String title = utils.getString();
		LOGGER.info("Please enter a value");
		float value = utils.getfloat();
		Item item = itemDAO.update(new Item(item_id, title, value));
		LOGGER.info("Item Updated");
		return item;
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the item you would like to delete");
		Long item_id = utils.getLong();
		return itemDAO.delete(item_id);
	}

	@Override
	public List<Item> readAll() {
		List<Item> item = itemDAO.readAll();
		for (Item item1 : item) {
			LOGGER.info(item1.toString());
		}
		return item;
	}

	
}
