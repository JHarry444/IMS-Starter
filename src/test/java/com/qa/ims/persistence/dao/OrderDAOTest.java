package com.qa.ims.persistence.dao;

import org.junit.jupiter.api.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class OrderDAOTest {
	
	private final OrderDAO orderDAO = new OrderDAO();
	private final CustomerDAO customerDAO = new CustomerDAO();
	Customer c1 = new Customer("Bill", "Johnson");
	Customer c2 = new Customer("Suzie", "Jones");
	Customer c3 = new Customer("Johnny", "Example");
	Customer added1 = customerDAO.create(c1);
	Customer added2 = customerDAO.create(c2);
	Customer added3 = customerDAO.create(c3);

	@BeforeEach
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	

	
	@Test
	public void createTest() {
		LocalDate ld = LocalDate.of(2021, 4, 25);
		Order o = new Order(added1.getId(), 25.99, ld);
		Order addedOrder = orderDAO.create(o);
		o.setId(addedOrder.getId());
		assertEquals(o, addedOrder);
	}
	
//	@Test
//	public void readTest() {
//		Item i = new Item("Mead", "100 Page Notebook- Blue", "1.49");
//		Item item = itemDAO.create(i);
//		Item testItem = itemDAO.read(item.getId());
//		assertEquals("Mead", testItem.getCompany());
//		assertEquals("100 Page Notebook- Blue", testItem.getProduct());
//		assertEquals("$1.49", testItem.getPriceString());
//		assertEquals(1.49, testItem.getPriceDouble());
//		assertNotEquals("$1.49", testItem.getPriceDouble());
//		Item testItem2 = itemDAO.read((long) -11111);
//		
//	}
//	
//	@Test
//	public void readAllTest() {
//		ArrayList<Item> rows = itemDAO.readAll();
//		for (Item i : rows) {
//			assertTrue(i instanceof Item);
//		}
//	}
//	
//	@Test
//	public void updateTest() {
//		Item i = new Item("Wilson", "Volleyball", "14.99");
//		Item testItem = itemDAO.create(i);
//		Long id = testItem.getId();
//		assertEquals(testItem.getCompany(), "Wilson");
//		assertEquals(testItem.getProduct(), "Volleyball");
//		assertEquals(testItem.getPriceString(), "$14.99");
//		Item updated = new Item(id, "Wilson", "Volleyball", "19.99");
//		Item updatedTestItem = itemDAO.update(updated);
//		assertEquals(19.99, updatedTestItem.getPriceDouble());
//	}
//	
//	@Test
//	public void deleteTest() {
//		Item i = new Item("Spaulding", "Basketball", "24.99");
//		Item testItem = itemDAO.create(i);
//		int deleted = itemDAO.delete(testItem.getId());
//		assertEquals(1, deleted);
//		int deleted2 = itemDAO.delete(testItem.getId());
//		assertEquals(0, deleted2);
//		int deleted3 = itemDAO.delete((long) -100);
//		assertEquals(0, deleted3);
//	}
}