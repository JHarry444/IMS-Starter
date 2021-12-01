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
        List<Item> items = itemDAO.readAll();
        for (Item item : items) {
            LOGGER.info(item);
        }
        return items;
    }

    @Override
    public Item read() {
        LOGGER.info("Please enter the id of the item you would like to read");
        Long id = utils.getLong();
        Item item = itemDAO.read(id);
        LOGGER.info(item);
        return item;
    }

    @Override
    public Item create() {
        LOGGER.info("Please enter a name");
        String name = utils.getString();
        LOGGER.info("Please enter a value");
        Integer value = Integer.valueOf(utils.getString());
        Item item = itemDAO.create(new Item(name, value));
        LOGGER.info("Item created");
        LOGGER.info(item);
        return item;
    }

    @Override
    public Item update() {
        LOGGER.info("Please enter the id of the item you would like to update");
        Long id = utils.getLong();
        LOGGER.info("Please enter a name");
        String name = utils.getString();
        LOGGER.info("Please enter a value");
        Integer value = Integer.valueOf(utils.getString());
        Item item = itemDAO.update(new Item(id, name, value));
        LOGGER.info("Customer Updated");
        return item;
    }

    @Override
    public int delete() {
        LOGGER.info("Please enter the id of the item you would like to delete");
        Long id = utils.getLong();
        return itemDAO.delete(id);
    }

    @Override
    public Item readByName() {
        LOGGER.info("Please enter the name of the item you would like to read");
        String name = utils.getString();
        Item item = itemDAO.readByName(name);
        LOGGER.info(item);
        return null;
    }
}
