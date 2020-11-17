package com.qa.ims.persistence;

import com.qa.ims.persistence.dao.*;

import java.util.ArrayList;
import java.util.List;

import com.qa.ims.controller.*;
import com.qa.ims.persistence.domain.*;

public class CloseOut {
	
	CustomerDAO customerDAO = new CustomerDAO();
	ItemDAO itemDAO = new ItemDAO();
	
	public List<Customer> GenerateCustomers() {
		List<Customer> customers = customerDAO.readAll();
		List<String> data = new ArrayList<String>();
		for (Customer customer : customers) {
			String sqldata =("insert into`ims`.`customers`(`first_name`,`surname`)values('"+customer.getFirstName()+"','"+customer.getSurname()+"');");
			data.add(sqldata);
		}
		System.out.println(data);
		return customers;
	}
	public List<Item> GenerateItems(){
		List<Item> items = itemDAO.readAll();
		List<String> data = new ArrayList<String>();
		for (Item item : items){
			String sqldata =("insert into`ims`.`items`(`title`,`price`)values('"+ item.getTitle()+"','"+item.getValue()+"');");
			data.add(sqldata);
		}
		System.out.println(data);
		return items;
		}
}
