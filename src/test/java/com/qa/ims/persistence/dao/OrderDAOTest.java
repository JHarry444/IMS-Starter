package com.qa.ims.persistence.dao;

import org.junit.jupiter.api.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
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
		Order o = new Order(added1.getId(), 25.99, LocalDate.of(2020, 2, 10));
		Order addedOrder = orderDAO.create(o);
		o.setId(addedOrder.getId());
		assertEquals(o, addedOrder);
	}
	
	@Test
	public void readTest() {
		Order i = new Order(added1.getId(), 29.55, LocalDate.of(2020, 11, 1));
		Order order = orderDAO.create(i);
		Order testOrder = orderDAO.read(order.getId());
		assertEquals(29.55, testOrder.getTotalDouble());
		assertEquals(added1.getId(), testOrder.getCustomerId());
		assertEquals("2020-11-01", testOrder.getOrderedOn());
		Order testOrder2 = orderDAO.read((long) -11111);
	}
	
	@Test
	public void readAllTest() {
		ArrayList<Order> rows = orderDAO.readAll();
		for (Order i : rows) {
			assertTrue(i instanceof Order);
		}
	}
	
	@Test
	public void updateTest() {
		Order o = new Order(added2.getId(), 102.34, LocalDate.of(2020, 5, 18));
		Order testOrder = orderDAO.create(o);
		Long id = testOrder.getId();
		assertEquals(testOrder.getCustomerId(), added2.getId());
		assertEquals(testOrder.getTotalDouble(), 102.34);
		assertEquals(testOrder.getOrderedOn(), "2020-05-18");
		Order updated = new Order(id, added2.getId(), 102.34, LocalDate.of(2020, 5, 19));
		Order updatedTestOrder = orderDAO.update(updated);
		assertEquals("2020-05-19", updatedTestOrder.getOrderedOn());
	}
	
	@Test
	public void deleteTest() {
		Order o = new Order(added3.getId(), 1.99, LocalDate.of(2020, 4, 14));
		Order testOrder = orderDAO.create(o);
		int deleted = orderDAO.delete(testOrder.getId());
		assertEquals(1, deleted);
		int deleted2 = orderDAO.delete(testOrder.getId());
		assertEquals(0, deleted2);
		int deleted3 = orderDAO.delete((long) -100);
		assertEquals(0, deleted3);
	}
}