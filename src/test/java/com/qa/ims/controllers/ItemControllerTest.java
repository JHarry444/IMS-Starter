package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
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
		Item updated = new Item("Bic", "Ball-Point Pen", "1.49");

		Mockito.when(this.utils.getLong()).thenReturn(null);
		Mockito.when(this.utils.getString()).thenReturn(updated.getCompany(), updated.getProduct(), updated.getPriceString());
		Mockito.when(this.dao.update(updated)).thenReturn(updated);

		assertEquals(updated, this.controller.update());

		Mockito.verify(this.utils, Mockito.times(1)).getLong();
		Mockito.verify(this.utils, Mockito.times(3)).getString();
		Mockito.verify(this.dao, Mockito.times(1)).update(updated);
	}

}