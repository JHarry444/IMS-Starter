package com.qa.ims.persistence;

import com.qa.ims.persistence.dao.*;

import java.util.List;

import com.qa.ims.controller.*;
import com.qa.ims.persistence.domain.*;

public class CloseOut {
	CustomerDAO customerDAO = new CustomerDAO();
	public void GenerateCustomers() {
		List<Customer> customers = customerDAO.readAll();
		for (Customer customer : customers) {
			String sqldata =("insert into`ims`.`customers`(`first_name`,`surname`)values('"+customer.getFirstName()+"','"+customer.getSurname()+"');");
			System.out.println(sqldata);
		}

	}
}
