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
		LOGGER.info("Please enter a company name");
		String companyName = utils.getString();
		LOGGER.info("Please enter a product name");
		String productName = utils.getString();
		LOGGER.info("Please enter a price");
		String price = utils.getString();
		Item item = itemDAO.create(new Item(companyName, productName, price));
		LOGGER.info("Customer created");
		return item;
	}
	
	public Item read() {
		LOGGER.info("Please enter the id of the item you'd like to read");
		Long id = utils.getLong();
		Item item = itemDAO.read(id);
		LOGGER.info(item);
		return item;
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
	public Item update() {
		LOGGER.info("Please enter the id of the item you'd like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter a company name");
		String company = utils.getString();
		LOGGER.info("Please enter a product name");
		String product = utils.getString();
		LOGGER.info("Please enter a price");
		String newPrice = utils.getString();
		Item item = itemDAO.update(new Item(id, company, product, newPrice));
		LOGGER.info("Item Updated");
		return item;
	}
	
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the item you would like to delete");
		Long id = utils.getLong();
		return itemDAO.delete(id);
	}

}
