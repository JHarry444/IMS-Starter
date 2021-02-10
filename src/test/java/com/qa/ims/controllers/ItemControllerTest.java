package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
	private ItemController controller;

	@Test
	public void createTest() { 
		final String item_name = "Mouse", item_desc = "Clicker";
		final Double item_cost = 8.0;
		final Item created = new Item(item_name, item_cost, item_desc);
		
		Mockito.when(utils.getString()).thenReturn(item_name, item_desc);
		Mockito.when(utils.getDouble()).thenReturn(item_cost);
		Mockito.when(itemDAO.create(created)).thenReturn(created);
		
		assertEquals(created, controller.create());  
		 
		Mockito.verify(utils, Mockito.times(2)).getString();
		Mockito.verify(utils, Mockito.times(1)).getDouble();
		Mockito.verify(itemDAO, Mockito.times(1)).create(created);
	}      
	   

	@Test 
	public void readAllTest() {
		List<Item> items = new ArrayList();
		items.add(new Item(1L, "Mouse", 8.0, "Clicker"));
		 
		Mockito.when(itemDAO.readAll()).thenReturn(items);
		
		assertEquals(items, controller.readAll());
		
		Mockito.verify(itemDAO, Mockito.times(1)).readAll();
	}
	 

	@Test
	public void updateTest() {
		Item updated = new Item(1L, "chris",8.0, "perrins");

		Mockito.when(this.utils.getLong()).thenReturn(1L);
		Mockito.when(this.utils.getString()).thenReturn(updated.getName(), updated.getDescription());
		Mockito.when(this.utils.getDouble()).thenReturn(updated.getCost());
		Mockito.when(this.itemDAO.update(updated)).thenReturn(updated);

		assertEquals(updated, this.controller.update());

		Mockito.verify(this.utils, Mockito.times(1)).getLong();
		Mockito.verify(this.utils, Mockito.times(2)).getString();
		Mockito.verify(this.utils, Mockito.times(1)).getDouble();
		Mockito.verify(this.itemDAO, Mockito.times(1)).update(updated); 
		
	}
	
	@Test
	public void deleteTest() {
		final long ID = 1L;
		
		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(itemDAO.delete(ID)).thenReturn(1);
		
		assertEquals(1L, this.controller.delete());
		
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(itemDAO, Mockito.times(1)).delete(ID);
	}

	
}
