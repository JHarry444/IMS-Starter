package com.qa.ims.controller;

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
	@Override
	public List<Item> readAll() {
		List<Item> items = itemDAO.readAll();
		for (Item item:items) {
			LOGGER.info(item.toString());
		}
		return null;
	}

	@Override
	public Item create() {
		LOGGER.info("Please enter the title");
		String title = utils.getString();
		LOGGER.info("Please enter price");
		Double price = utils.getDouble();
		Item item = itemDAO.create(new Item(title,price));
		return null;
	}

	@Override
	public Item update() {
		LOGGER.info("Please enter the id of the item you'd like to edit");
		Long id  = utils.getLong();
		LOGGER.info("Please enter a new title");
		String title = utils.getString();
		LOGGER.info("Please enter a new price");
		Double price = utils.getDouble();
		Item item = itemDAO.update(new Item(id,title,price));
		return null;
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the item you'd like to delete");
		Long id = utils.getLong();
		return ItemDAO.delete(id);
	}
	
}
