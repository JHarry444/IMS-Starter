package com.qa.ims.persist;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.*;
import com.qa.ims.persistence.*;
import com.qa.ims.persistence.dao.*;
import com.qa.ims.persistence.domain.*;
import com.qa.ims.utils.DBUtils;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class GenerateSQLTest {
	
	@Mock
	private Utils utils;
	
	@Mock
	private ItemDAO itemDAO;
	
	@Mock
	private OrderDAO orderDAO;
	
	@Mock
	private CustomerDAO customerDAO;
	
	@Mock
	private Order_ItemDAO order_itemDAO;
	
	@InjectMocks
	private GenerateSQL generator;
	
	@Test
	public void TestGenerateCustomers() {
		final Customer cust1 = new Customer(1l,"tom","smith");
		final Customer cust2 = new Customer(2l,"jordan","harrison");
		final List<Customer> customers = new ArrayList<Customer>(); 
		customers.add(cust1);
		customers.add(cust2);
		List<String> data = new ArrayList<String>();
		for (Customer customer : customers) {
			String sqldata =("insert into`ims`.`customers`(`CustomerID`,`first_name`,`surname`)values("+customer.getId()+",'"+customer.getFirstName()+"','"+customer.getSurname()+"');\r");
			data.add(sqldata);
		}
		
		Mockito.when(customerDAO.readAll()).thenReturn(customers);
		
		assertEquals(data,generator.GenerateCustomers());
	}
	
	@Test
	public void TestGenerateItems() {
		final Item item1 = new Item(1l,"cheese",10.98);
		final Item item2 = new Item(2l,"chips", 98.0);
		final List<Item> items = new ArrayList<Item>(); 
		items.add(item1);
		items.add(item2);
		List<String> data = new ArrayList<String>();
		for (Item item : items){
			String sqldata =("insert into`ims`.`items`(`ItemID`,`title`,`price`)values("+item.getID()+",'" +item.getTitle()+"',"+item.getValue()+");\r");
			data.add(sqldata);
		}
		
		Mockito.when(itemDAO.readAll()).thenReturn(items);
		
		assertEquals(data,generator.GenerateItems());
	}
	
	@Test
	public void TestGenerateOrders() {
		final Order order1 = new Order(1l,1L);
		final Order order2 = new Order(2l,2L);
		final List<Order> orders = new ArrayList<Order>(); 
		orders.add(order1);
		orders.add(order2);
		List<String> data = new ArrayList<String>();
		for (Order order :orders) {
			String sqldata = ("insert into `ims`.`orders`(`OrderID`,`CustomerID`) VALUES("+order.getID()+","+order.getCustomerID()+";\r");
			data.add(sqldata);
		}
		
		Mockito.when(orderDAO.readAll()).thenReturn(orders);
		
		assertEquals(data,generator.GenerateOrders());
	}
	
	@Test
	public void TestGenerateOrder_Item() {
		final Order order1 = new Order(1l,1L);
		final Order order2 = new Order(2l,2L);
		final List<Order> orders = new ArrayList<Order>(); 
		orders.add(order1);
		orders.add(order2);
		final Item item1 = new Item(1l,"cheese",10.98);
		final List<Item> OI1 = new ArrayList<Item>();
		final List<Item> OI2 = new ArrayList<Item>();
		OI1.add(item1);
		OI1.add(item1);
		OI2.add(item1);
		List<String> data = new ArrayList<String>();
		
				String sqldata1 =("insert into `ims`.`order_item`(`OrderID`,`ItemID`)VALUES("+order1.getID()+","+item1.getID()+");\r");
				data.add(sqldata1);	
				data.add(sqldata1);
				String sqldata2 =("insert into `ims`.`order_item`(`OrderID`,`ItemID`)VALUES("+order2.getID()+","+item1.getID()+");\r");
				data.add(sqldata2);
			
		Mockito.when(orderDAO.readAll()).thenReturn(orders);
		Mockito.when(order_itemDAO.readItemsByOrder(Mockito.anyLong())).thenReturn(OI1,OI2);
		assertEquals(data,generator.GenerateOrder_Item());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
