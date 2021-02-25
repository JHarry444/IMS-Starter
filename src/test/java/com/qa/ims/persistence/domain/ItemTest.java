package com.qa.ims.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.DecimalFormat;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class ItemTest {
    
    @Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Item.class).verify();
	}

	@Test
	public void testItem() {
		Item item = new Item("apple", 1.5D);
		assertNotNull(item);
		assertEquals("apple", item.getName());
		assertEquals(1.5D, item.getPrice());
	}

	@Test
	public void testItemWithID() {
		Item item = new Item(1L, "apple", 1.5D);
		assertNotNull(item);
		assertEquals("apple", item.getName());
		assertEquals(1.5D, item.getPrice());
	}

	@Test
	public void testGetId() {
		Item item = new Item(1L, "apple", 1.5D);
		assertEquals(1L, item.getId());
	}

	@Test
	public void testSetId() {
		Item item = new Item(1L, "apple", 1.5D);
		item.setId(2L);
		assertEquals(2L, item.getId());
	}

	@Test
	public void testGetName() {
		Item item = new Item(1L, "apple", 1.5D);
		assertEquals("apple", item.getName());
	}

	@Test
	public void testSetName() {
		Item item = new Item(1L, "apple", 1.5D);
		item.setName("banana");
		assertEquals("banana", item.getName());
	}

	@Test
	public void testGetPrice() {
		Item item = new Item(1L, "apple", 1.5D);
		assertEquals(1.5D, item.getPrice());
	}

	@Test
	public void testSetPrice() {
		Item item = new Item(1L, "apple", 1.5D);
		item.setPrice(2.5D);
		assertEquals(2.5D, item.getPrice());
	}

    @Test
    public void testGetFormattedPrice() {
        Item item = new Item(1L, "apple", 1.5D);
        final DecimalFormat decim = new DecimalFormat("0.00");
        assertEquals(decim.format(item.getPrice()), item.getFormattedPrice());
    }

	@Test
	public void testGetString() {
		Item item = new Item(1L, "apple", 1.5D);
		assertEquals("id:" + item.getId() + " name:" + item.getName() + " price:" + item.getPrice(), item.toString());
	}

	@Test
	public void testGetFormattedString() {
		Item item = new Item(1L, "apple", 1.5D);
		assertEquals("ID: " + item.getId() + ", Name: " + item.getName() + ", Price: " + item.getFormattedPrice(), item.formattedString());
	}

}
