package com.qa.ims.persistence;

import com.qa.ims.persistence.dao.*;

import java.util.ArrayList;
import java.util.List;

import com.qa.ims.controller.*;
import com.qa.ims.persistence.domain.*;

public class CloseOut {
	
	CustomerDAO customerDAO = new CustomerDAO();
	ItemDAO itemDAO = new ItemDAO();
	OrderDAO orderDAO = new OrderDAO();
	
	public List<Customer> GenerateCustomers() {
		List<Customer> customers = customerDAO.readAll();
		List<String> data = new ArrayList<String>();
		for (Customer customer : customers) {
			String sqldata =("insert into`ims`.`customers`(`CustomerID`,`first_name`,`surname`)values("+customer.getId()+",'"+customer.getFirstName()+"','"+customer.getSurname()+"');");
			data.add(sqldata);
		}
		System.out.println(data);
		return customers;
	}
	public List<Item> GenerateItems(){
		List<Item> items = itemDAO.readAll();
		List<String> data = new ArrayList<String>();
		for (Item item : items){
			String sqldata =("insert into`ims`.`items`(`ItemID`,`title`,`price`)values("+item.getID()+",'" +item.getTitle()+"',"+item.getValue()+");");
			data.add(sqldata);
		}
		System.out.println(data);
		return items;
		}
	public List<Order> GenerateOrders(){
		List <Order> orders = orderDAO.readAll();
		List<String> data = new ArrayList<String>();
		for (Order order :orders) {
			String sqldata = ("insert into `ims`.`orders`(`OrderID`,`CustomerID`) VALUES("+order.getID()+","+order.getCustomerID()+";");
			data.add(sqldata);
		}
		System.out.println(data);
		return orders;
	}
	
}
