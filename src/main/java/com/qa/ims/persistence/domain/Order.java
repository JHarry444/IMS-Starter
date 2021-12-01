package com.qa.ims.persistence.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import com.qa.ims.persistence.dao.ItemDAO;

public class Order {
    private Long order_id;
    private Long customer_id;
    private List<Item> item_list;
    private ItemDAO itemDAO;
    public static final Logger LOGGER = LogManager.getLogger();

    public Order(Long order_id, Long customer_id) {
        this.setOrderId(order_id);
        this.setCustomerId(customer_id);
        this.item_list = new ArrayList<>();
        this.itemDAO = new ItemDAO();
    }

    public Long getOrderId() {
        return this.order_id;
    }

    public void setOrderId(Long id) {
        this.order_id = id;
    }

    public Long getCustomerId() {
        return this.customer_id;
    }

    public void setCustomerId(Long id) {
        this.customer_id = id;
    }

    public List<Item> getItemList() { return this.item_list; }

    public void setItemList(String name, Double value) {
        Item item = this.itemDAO.create(new Item(name, value));
        LOGGER.info("Item created");
        LOGGER.info(item);
        this.item_list.add(item);
    }

    public void removeItemList(Long itemId) {
        for (Item item : this.item_list) {
            if (item.getId().equals(itemId)) {
                this.item_list.remove(item);
            } else {
                LOGGER.info("itemId does not exist in Order");
            }
        }
    }

    @Override
    public String toString() {
        return "order id:" + order_id + " customer id:" + customer_id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((order_id == null) ? 0 : order_id.hashCode());
        result = prime * result + ((customer_id == null) ? 0 : customer_id.hashCode());
        return result;
    }

    public void

}
