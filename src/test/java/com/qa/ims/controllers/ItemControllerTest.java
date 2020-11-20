package com.qa.ims.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
	private ItemController controller;
	
	@Test
	public void testCreate() {
		final String item_name = "Picture Frame";
		final Double item_price = 17.50;
		final Item created = new Item(item_name, item_price);
		
		Mockito.when(utils.getString()).thenReturn(item_name);
		Mockito.when(utils.getDouble()).thenReturn(item_price);
		Mockito.when(itemDAO.create(created)).thenReturn(created);
		
		assertEquals(created, controller.create());
		
		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(utils, Mockito.times(1)).getDouble();
		Mockito.verify(itemDAO, Mockito.times(1)).create(created);
	}
	
	@Test 
	public void testReadAll() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(1L, "Table", 89.99));
		
		Mockito.when(itemDAO.readAll()).thenReturn(items);
		
		assertEquals(items, controller.readAll());
		
		Mockito.verify(itemDAO, Mockito.times(1)).readAll();
	}
	
	@Test
	public void testUpdate() {
		Item updated = new Item(1L, "Phone", 499.99);
		
		Mockito.when(this.utils.getLong()).thenReturn(1L);
		Mockito.when(this.utils.getString()).thenReturn(updated.getItem_name());
		Mockito.when(this.utils.getDouble()).thenReturn(updated.getItem_price());
		Mockito.when(this.itemDAO.update(updated)).thenReturn(updated);
		
		assertEquals(updated, this.controller.update());
		
		Mockito.verify(this.utils, Mockito.times(1)).getLong();
		Mockito.verify(this.utils, Mockito.times(1)).getString();
		Mockito.verify(this.utils, Mockito.times(1)).getDouble();
		Mockito.verify(this.itemDAO, Mockito.times(1)).update(updated);
	}
	
	@Test
	public void testDelete() {
		final long Item_id = 1L;
		
		Mockito.when(utils.getLong()).thenReturn(Item_id);
		Mockito.when(itemDAO.delete(Item_id)).thenReturn(1);
		
		assertEquals(1L, this.controller.delete());
		
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(itemDAO, Mockito.times(1)).delete(Item_id);
	}
}
