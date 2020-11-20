package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.ItemController;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {
	@Mock
	private Utils utils;

	@Mock
	private ItemDAO itemDAO;

	@InjectMocks
	private ItemController itemController;

	@Test
	public void testCreate() {
		final String ITEM_NAME = "chess set";
		final double ITEM_PRICE = 12.99;
		final long QUANTITY = 7l, ID = 5l;
		final Item created = new Item(ID, ITEM_NAME, ITEM_PRICE, QUANTITY);

		when(utils.getString()).thenReturn(ITEM_NAME);
		when(utils.getDouble()).thenReturn(ITEM_PRICE);
		when(utils.getLong()).thenReturn(QUANTITY);
		when(itemDAO.create(any(Item.class))).thenReturn(created);

		assertEquals(created, itemController.create());

		verify(utils, times(1)).getString();
		verify(utils, times(1)).getDouble();
		verify(utils, times(1)).getLong();
		verify(itemDAO, times(1)).create(any(Item.class));
	}

	@Test
	public void testReadAll() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(5l, "chess set", 12.99, 7l));

		when(itemDAO.readAll()).thenReturn(items);

		assertEquals(items, itemController.readAll());

		verify(itemDAO, times(1)).readAll();
	}

	@Test
	public void testUpdate() {
		final String ITEM_NAME = "chess set";
		final double ITEM_PRICE = 12.99;
		final long QUANTITY = 7l, ID = 5l;
		final Item updated = new Item(ID, ITEM_NAME, ITEM_PRICE, QUANTITY);

		when(utils.getString()).thenReturn(ITEM_NAME);
		when(utils.getDouble()).thenReturn(ITEM_PRICE);
		when(utils.getLong()).thenReturn(null, ID, QUANTITY);
		when(itemDAO.update(any(Item.class))).thenReturn(updated);
		when(itemDAO.readItem(ID)).thenReturn(updated);
		when(itemDAO.readItem(null)).thenReturn(null);

		assertEquals(updated, itemController.update());

		verify(utils, times(1)).getString();
		verify(utils, times(1)).getDouble();
		verify(utils, times(3)).getLong();
		verify(itemDAO, times(1)).update(updated);
		verify(itemDAO, times(1)).readItem(anyLong());
		verify(itemDAO, times(1)).readItem(null);
	}

	@Test
	public void testDelete() {
		final long ID = 5L;
		List<Item> items = new ArrayList<>();
		items.add(new Item(ID, "chess set", 12.99, 7l));

		when(itemDAO.readAll()).thenReturn(items);
		when(utils.getLong()).thenReturn(ID);
		when(itemDAO.delete(ID)).thenReturn(1);
		
		assertEquals(1l, itemController.delete());
		
		verify(itemDAO, times(1)).readAll();
		verify(utils, times(1)).getLong();
		verify(itemDAO, times(1)).delete(anyLong());
	}
}
