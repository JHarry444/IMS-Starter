package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOtest {
	
	private Item harmonica = new Item(1l, "harmonica", 1200.99, 8l);
	private Item wheelieBin = new Item(2l, "wheelie bin", 2.99, 84528l);
	private Item mother = new Item(3l, "your mother", 0.99, 1l);
	private Item nike = new Item(4l, "nike airs", 40.99, 74l);
	private final OrderDAO DAO = new OrderDAO();
	private final ItemDAO itemDAO = new ItemDAO();

	@BeforeClass
	public static void init() {
		DBUtils.connect("root", "root");
	}

	@Before
	public void setup() {
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() {
		List<Item> items = new ArrayList<>();
		final Order created = new Order(3l,3l,items);
		
		assertEquals(created, DAO.create(created));
	}
	@Test
	public void testCreateLine() {
		long orderID = 1l;
		long itemID = 3l;
		
		Item itemIterated = new Item(3l,"your mother", 0.99, 0l);
		DAO.createLine(orderID, itemID);
		assertEquals(itemIterated, itemDAO.readItem(itemID));
	}

	@Test
	public void testReadAll() {
		List<Item> items1 = new ArrayList<>();
		List<Item> items2 = new ArrayList<>();
		List<Order> expected = new ArrayList<>();
		final Order order1 = new Order(1l,3l,items1);
		final Order order2 = new Order(2l,1l,items2);
		order1.getItems().add(nike);
		order1.getItems().add(harmonica);
		order1.getItems().add(wheelieBin);
		order2.getItems().add(wheelieBin);
		order2.getItems().add(wheelieBin);
		order2.getItems().add(wheelieBin);
		order2.getItems().add(wheelieBin);
		order2.getItems().add(wheelieBin);
		order2.getItems().add(wheelieBin);
		order2.getItems().add(wheelieBin);
		order2.getItems().add(wheelieBin);
		order2.getItems().add(wheelieBin);
		order2.getItems().add(wheelieBin);
		
		expected.add(order2);
		expected.add(order1);
		
		for(Order order: expected) {
			for(Item item: order.getItems()) {
				item.setQuantity(1l);
			}
		}
		
		
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		List<Item> items = new ArrayList<>();
		items.add(wheelieBin);
		items.add(wheelieBin);
		items.add(wheelieBin);
		items.add(wheelieBin);
		items.add(wheelieBin);
		items.add(wheelieBin);
		items.add(wheelieBin);
		items.add(wheelieBin);
		items.add(wheelieBin);
		items.add(wheelieBin);
		final Order expected = new Order(2l,1l,items);
		for(Item item: expected.getItems()) {
			item.setQuantity(1l);
		}
		assertEquals(expected, DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		List<Item> items = new ArrayList<>();
		final Order order = new Order(1l,3l,items);
		order.getItems().add(nike);
		order.getItems().add(harmonica);
		order.getItems().add(wheelieBin);
		for(Item item: order.getItems()) {
			item.setQuantity(1l);
		}
		assertEquals(order, DAO.readOrder(ID));
	}
	
	@Test
	public void testReadLine() {
		final long ID = 1L;
		List<Item> items = new ArrayList<>();
		items.add(nike);
		items.add(harmonica);
		items.add(wheelieBin);
		assertEquals(items, DAO.readLines(ID));
	}

	@Test
	public void testUpdate() {
		List<Item> items = new ArrayList<>();
		final Order updated = new Order(1l,2l,items);
		updated.getItems().add(nike);
		updated.getItems().add(harmonica);
		updated.getItems().add(wheelieBin);
		for(Item item: updated.getItems()) {
			item.setQuantity(1l);
		}
		assertEquals(updated, DAO.update(updated));
	}

	@Test
	public void testDelete() {
		List<Item> items = new ArrayList<>();
		final Order created = new Order(3l,3l,items);
		DAO.create(created);
		assertEquals(1, DAO.delete(3));
	}
	@Test
	public void testDeleteOrderLines() {
		final long ID = 1L;
		assertEquals(3, DAO.deleteOrderLines(ID));
	}
	@Test
	public void testDeleteLine() {
		final long orderID = 1L;
		final long itemID = 4L;
		assertEquals(1, DAO.deleteLine(orderID, itemID));
	}
}
