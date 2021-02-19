package com.qa.ims.controller;

import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ItemController implements CrudController<Item>{


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
        return null;
    }

    @Override
    public Item create() {
        LOGGER.info("Please enter an item name");
        String name = utils.getString();
        LOGGER.info("Please enter the price for "+name);
        double price = utils.getDouble();
        Item item = itemDAO.create(new Item(name, price));
        LOGGER.info("item created");
        return item;
    }

    @Override
    public Item update() {
        return null;
    }

    @Override
    public int delete() {
        return 0;
    }
}
