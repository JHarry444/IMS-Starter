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
	public void testCreate() {
		final String productName = "SAWAYAMA", artistName = "Rina Sawayama";
		final Double releaseDate = 20200417.0, productPrice = 9.99, productQty = 300.0;
		final Item created = new Item(productName, artistName, releaseDate, productPrice, productQty);

		Mockito.when(utils.getString()).thenReturn(productName, artistName);
		Mockito.when(utils.getDouble()).thenReturn(releaseDate, productPrice, productQty);
		Mockito.when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());

		Mockito.verify(utils, Mockito.times(2)).getString();
		Mockito.verify(utils, Mockito.times(3)).getDouble();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}

	@Test
	public void testReadAll() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(1L, "SAWAYAMA","Rina Sawayama", 20200417.0, 9.99, 300.0));

		Mockito.when(dao.readAll()).thenReturn(items);

		assertEquals(items, controller.readAll());

		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

	@Test
	public void testUpdate() {
		Item updated = new Item(1L,"SAWAYAMA (Deluxe Edition)","Rina Sawayama", 20200417.0, 9.99, 299.0);

		Mockito.when(this.utils.getLong()).thenReturn(1L);
		Mockito.when(this.utils.getString()).thenReturn(updated.getProductName(), updated.getArtistName());
		Mockito.when(this.utils.getDouble()).thenReturn(updated.getReleaseDate(), updated.getProductPrice(), updated.getProductQty());
		Mockito.when(this.dao.update(updated)).thenReturn(updated);

		assertEquals(updated, this.controller.update());

		Mockito.verify(this.utils, Mockito.times(1)).getLong();
		Mockito.verify(this.utils, Mockito.times(2)).getString();
		Mockito.verify(this.utils, Mockito.times(3)).getDouble();
		Mockito.verify(this.dao, Mockito.times(1)).update(updated);
	}

	@Test
	public void testDelete() {
		final long productID = 1L;

		Mockito.when(utils.getLong()).thenReturn(productID);
		Mockito.when(dao.delete(productID)).thenReturn(1);

		assertEquals(1L, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(productID);
	}

}