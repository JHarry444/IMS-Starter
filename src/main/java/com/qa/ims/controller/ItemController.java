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
		LOGGER.info("Please enter the product name");
		String productName = utils.getString();
		LOGGER.info("Please enter the artist's name");
		String artistName = utils.getString();
		LOGGER.info("Please enter the album release date");
		Double releaseDate = utils.getDouble();
		LOGGER.info("Please enter the price of the item");
		Double productPrice = utils.getDouble();
		LOGGER.info("Please enter the quantity of items available");
		Double productQty = utils.getDouble();
		Item item = itemDAO.create(new Item(productName, artistName, releaseDate, productPrice, productQty));
		LOGGER.info("Item created");
		return item;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Item update() {
		LOGGER.info("Please enter the ID of the item you would like to update");
		Long productID = utils.getLong();
		LOGGER.info("Please enter the product name");
		String productName = utils.getString();
		LOGGER.info("Please enter the artist's name");
		String artistName = utils.getString();
		LOGGER.info("Please enter the album release date");
		Double releaseDate = utils.getDouble();
		LOGGER.info("Please enter the price of the item");
		Double productPrice = utils.getDouble();
		LOGGER.info("Please enter the quantity of items available");
		Double productQty = utils.getDouble();
		Item item = itemDAO.update(new Item(productID, productName, artistName, releaseDate, productPrice, productQty));
		LOGGER.info("Item updated");
		return item;
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the ID of the item you would like to delete");
		Long productID = utils.getLong();
		return itemDAO.delete(productID);
	}

}