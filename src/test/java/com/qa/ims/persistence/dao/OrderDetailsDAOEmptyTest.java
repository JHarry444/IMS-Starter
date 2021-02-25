package com.qa.ims.persistence.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.qa.ims.persistence.domain.OrderDetail;
import com.qa.ims.utils.DBUtils;

public class OrderDetailsDAOEmptyTest {
        
    private final OrderDetailDAO DAO = new OrderDetailDAO();

	@BeforeAll
	public static void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema-empty.sql");
	}

	@Test
	public void testReadAll() {
		List<OrderDetail> expected = new ArrayList<>();
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertEquals(null, DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals(null, DAO.read(ID));
	}

    @Test
    public void testReadOrder() {
        List<OrderDetail> expected = new ArrayList<>();
		assertEquals(expected, DAO.readOrder(1L));
    }

    @Test
    public void testReadOrderItem() {
        assertEquals(null, DAO.readOrderItem(1L, 1L));
    }

    @Test
    public void testGetPrice() {
        assertEquals(0D, DAO.getPrice(150L));
    }

	@Test
	public void testUpdate() {
		final OrderDetail updated = new OrderDetail(2L, 2L, 2L, 20L);
		assertEquals(null, DAO.update(updated));
	}

	@Test
	public void testDelete() {
		assertEquals(0, DAO.delete(1));
	}

}
