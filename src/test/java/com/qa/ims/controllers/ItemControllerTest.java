package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.ItemController;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.utils.DBUtils;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {
	
	@Mock
	private Utils utils;
	
	@Mock
	private ItemDAO DAO;
	
	@InjectMocks
	private ItemController controller;
	
	@Test
	public void TestCreate() {
		final String title = "chips";
		final Double price = 23.45D;
		final Item created = new Item(title,price);
		
		Mockito.when(utils.getString()).thenReturn(title);
		Mockito.when(utils.getDouble()).thenReturn(price);
		Mockito.when(DAO.create(created)).thenReturn(created);
		
		assertEquals(created,controller.create());
		
		Mockito.verify(utils,Mockito.times(1)).getString();
		Mockito.verify(utils,Mockito.times(1)).getDouble();
		Mockito.verify(DAO,Mockito.times(1)).create(created);
		
	}
	
	@Test
	public void TestUpdate() {
		final Long id = 2L;
		final String title = "beans";
		final Double price = 34.55D;
		final Item updated = new Item(id,title,price);
		
		Mockito.when(utils.getLong()).thenReturn(id);
		Mockito.when(utils.getString()).thenReturn(title);
		Mockito.when(utils.getDouble()).thenReturn(price);
		Mockito.when(DAO.update(updated)).thenReturn(updated);
		
		assertEquals(updated,controller.update());
		
		Mockito.verify(utils,Mockito.times(1)).getLong();
		Mockito.verify(utils,Mockito.times(1)).getString();
		Mockito.verify(utils,Mockito.times(1)).getDouble();
		Mockito.verify(DAO,Mockito.times(1)).update(updated);
	}
	
	@Test
	public void TestDelete() {
		final Long ID = 1L;
		
		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(DAO.delete(ID)).thenReturn(0);
		
		assertEquals(0,controller.delete());
		
		Mockito.verify(utils,Mockito.times(1)).getLong();
		Mockito.verify(DAO,Mockito.times(1)).delete(ID);
	}
	
	@Test
	public void TestReadAll() {
		final List<Item> items = new ArrayList<Item>();
		final Item read = new Item(1l,"porridegeg",23.965D);
		items.add(read);
		
		Mockito.when(DAO.readAll()).thenReturn(items);
		
		assertEquals(items,controller.readAll());
		
		Mockito.verify(DAO,Mockito.times(1)).readAll();
	}
	
}
