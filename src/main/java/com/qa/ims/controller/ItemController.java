package com.qa.ims.controller;

import java.util.List;
import java.math.BigDecimal;
import java.sql.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemsDAO;
import com.qa.ims.persistence.domain.Items;

import com.qa.ims.utils.Utils;

/**
 * Takes in item details for CRUD functionality
 *
 */
public class ItemsController implements CrudController<Items> {

	public static final Logger LOGGER = LogManager.getLogger();

	private ItemsDAO itemsDAO;
	private Utils utils;

	public ItemsController(ItemsDAO itemsDAO, Utils utils) {
		super();
		this.itemsDAO = itemsDAO;
		this.utils = utils;
	}

	/**
	 * Reads all items to the logger
	 */
	@Override
	public List<Items> readAll() {
		List<Items> items = itemsDAO.readAll();
		for (Items item : items) {
			LOGGER.info(item.toString());
		}
		
		return items;
	}

	/**
	 * Creates an item by taking in user input
	 */
	@Override
	public Items create() {
		LOGGER.info("Please enter an item name");
		String productName = utils.getString();
		LOGGER.info("Please enter the artist's name");
		String artist = utils.getString();
		LOGGER.info("Please enter the album release date");
		Date releaseDate = utils.getDate();
		LOGGER.info("Please enter the price of the item");
		BigDecimal productPrice = utils.getBigDecimal();
		LOGGER.info("Please enter the quantity of items available");
		Double productQty = utils.getDouble();
		Items items = itemsDAO.create(new Items(productName, artist, releaseDate, productPrice, productQty));
		LOGGER.info("Item created");
		return items;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Items update() {
		LOGGER.info("Please enter the ID of the item you would like to update");
		Long productID = utils.getLong();
		LOGGER.info("Please enter an item name");
		String productName = utils.getString();
		LOGGER.info("Please enter the artist's name");
		String artist = utils.getString();
		LOGGER.info("Please enter the album release date");
		Date releaseDate = utils.getDate();
		LOGGER.info("Please enter the price of the item");
		BigDecimal productPrice = utils.getBigDecimal();
		LOGGER.info("Please enter the quantity of items available");
		Double productQty = utils.getDouble();
		Items items = itemsDAO.update(new Items(productID, productName, artist, releaseDate, productPrice, productQty));
		LOGGER.info("Item Updated");
		return items;
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
		return itemsDAO.delete(productID);
	}

}
