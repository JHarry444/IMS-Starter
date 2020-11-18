package com.qa.ims.persistence;

import com.qa.ims.persistence.dao.*;

import java.util.ArrayList;
import java.util.List;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.controller.*;
import com.qa.ims.persistence.domain.*;

public class GenerateSQL {
	public static final Logger LOGGER = LogManager.getLogger();
	CustomerDAO customerDAO = new CustomerDAO();
	ItemDAO itemDAO = new ItemDAO();
	OrderDAO orderDAO = new OrderDAO();
	Order_ItemDAO order_itemDAO = new Order_ItemDAO();
	
	public List<String> GenerateCustomers() {
		List<Customer> customers = customerDAO.readAll();
		List<String> data = new ArrayList<String>();
		for (Customer customer : customers) {
			String sqldata =("insert into`ims`.`customers`(`CustomerID`,`first_name`,`surname`)values("+customer.getId()+",'"+customer.getFirstName()+"','"+customer.getSurname()+"');\r");
			data.add(sqldata);
		}
		return data;
		
	}
	public List<String> GenerateItems(){
		List<Item> items = itemDAO.readAll();
		List<String> data = new ArrayList<String>();
		for (Item item : items){
			String sqldata =("insert into`ims`.`items`(`ItemID`,`title`,`price`)values("+item.getID()+",'" +item.getTitle()+"',"+item.getValue()+");\r");
			data.add(sqldata);
		}
		return data;
		}
	public List<String> GenerateOrders(){
		List <Order> orders = orderDAO.readAll();
		List<String> data = new ArrayList<String>();
		for (Order order :orders) {
			String sqldata = ("insert into `ims`.`orders`(`OrderID`,`CustomerID`) VALUES("+order.getID()+","+order.getCustomerID()+";\r");
			data.add(sqldata);
		}
		return data;
	}
	public List<String> GenerateOrder_Item(){
		List<Order> orders = orderDAO.readAll();
		List<String> data = new ArrayList<String>();
		for (Order order:orders) {
			List<Item> items = order_itemDAO.readItemsByOrder(order.getID());
			for(Item item : items) {
				String sqldata =("insert into `ims`.`order_item`(`OrderID`,`ItemID`)VALUES("+order.getID()+","+item.getID()+");\r");
				data.add(sqldata);	
			}
		}
		return data;
	}
	
	
	
}
