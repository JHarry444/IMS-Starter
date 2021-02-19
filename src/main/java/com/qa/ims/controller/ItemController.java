package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;



public class ItemController implements CrudController {

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
		for (Item item : items) {
			LOGGER.info(item.toString());
		}
		return items;
	}

	@Override
	public Object create() {
		LOGGER.info("Please enter a product name");
		String product = utils.getString();
		LOGGER.info("Please enter a price");
		Double price = utils.getDouble();
		Item item = itemDAO.create(new Item(product, price));
		LOGGER.info("Item created");
		return item;
			}

	@Override
	public Object update() {
		LOGGER.info("Please enter the item id of the product you would like to update");
		Long itemid = utils.getLong();
		LOGGER.info("Please enter a product name");
		String product = utils.getString();
		LOGGER.info("Please enter a price");
		Double price  = utils.getDouble();
		Item item = itemDAO.update(new Item(itemid, product, price));
		LOGGER.info("Customer Updated");
		return item;
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the item you would like to delete");
		Long itemid = utils.getLong();
		return itemDAO.delete(itemid);
	} 	
	
}
