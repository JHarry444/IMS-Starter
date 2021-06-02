package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.ItemController;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private ItemDAO dao;

	@InjectMocks
	private ItemController controller;

	@Test
	public void createTest() {
		ItemController controller = new ItemController(dao, utils);
		final String COMPANY = "Wilson", PRODUCT = "Basketball", PRICE = "19.99";
		final Item created = new Item(COMPANY, PRODUCT, PRICE);

		Mockito.when(utils.getString()).thenReturn(COMPANY, PRODUCT, PRICE);
		Mockito.when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(3)).getString();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}

	@Test
	public void readAllTest() {
		ArrayList<Item> items = new ArrayList<Item>();
		items.add(new Item("Spaulding", "Volleyball", "14.99"));

		Mockito.when(dao.readAll()).thenReturn(items);

		assertEquals(items, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

	@Test
	public void updateTest() {
		Item i = new Item((long) 12, "Bic", "Ball-Point Pen", "1.49");

		Mockito.when(this.utils.getLong()).thenReturn((long) 12);
		Mockito.when(this.utils.getString()).thenReturn(i.getCompany(), i.getProduct(), i.getPriceDouble() + "");
		Mockito.when(this.dao.update(i)).thenReturn(i);
		
		Item updated = controller.update();
		
		assertEquals(i.getId(),updated.getId());
		assertEquals(i.getCompany(), updated.getCompany());
		assertTrue(i instanceof Item);

		Mockito.verify(this.utils, Mockito.times(1)).getLong();
		Mockito.verify(this.utils, Mockito.times(3)).getString();
		Mockito.verify(this.dao, Mockito.times(1)).update(updated);
	}
	
	@Test
	public void deleteTest() {
		Item i2 = new Item((long) 22, "Wilson", "Basketball", 19.99);
		
		Mockito.when(this.utils.getLong()).thenReturn((long) 22);
		Mockito.when(this.dao.delete(22)).thenReturn(1);
		
		int result = controller.delete();
		assertEquals(1, result);
		
		Mockito.verify(this.utils, Mockito.times(1)).getLong();
		Mockito.verify(this.dao, Mockito.times(1)).delete(22);
	}

}