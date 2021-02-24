package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ItemTest {
	
	private static Item testItem;
	
	@Test
	public void firstItemConstructorTest() {
		testItem = new Item("peanuts", 0.70f);
		
		assertNotNull(testItem);
		assertTrue(testItem instanceof Item);
	}
	
	@Test
	public void secondItemConstructorTest() {
		testItem = new Item(1l, "banana", 1.20f);
		assertNotNull(testItem);
	}
	
	@Test
	public void getItem_idTest() {
		Item tes = new Item(1l, "Kevin", 1.20f);
		assertSame(1l,tes.getItem_id());
	}
	
	@Test
	public void setItem_idTest() {
		Item tes = new Item(1l,"peanuts", 0.85f);
		tes.setItem_id(1l);
		assertEquals(1l, tes.getItem_id());
	}
	
	@Test
	public void getTitleTest() {
		Item tes = new Item(1l, "Kevin", 1.20f);
		assertSame("Kevin",tes.getTitle());
	}
	
	@Test
	public void getValueTest() {
		Item tes = new Item(1l, "Kevin", 1.2f);
		assertEquals(1.2f, tes.getValue());
	}
	
	@Test
	public void toStringTest() {
		Item tes = new Item(1l, "Kevin", 1.2f);
		assertNotNull("item_id:1,title:pepsi,value:1.25f", tes.toString());
	}

}
