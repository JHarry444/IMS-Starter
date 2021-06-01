package com.qa.ims.persistence.dao;

import org.junit.jupiter.api.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class ItemDAOTest {
	
	private final ItemDAO itemDAO = new ItemDAO();

	@BeforeEach
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void creatTest() {
		Item item = new Item("Bic", "Ball-Point Pen", "1.29");
		Item item2 = itemDAO.create(item);
		item.setId(item2.getId());
		assertEquals(item, item2);
	}
}
