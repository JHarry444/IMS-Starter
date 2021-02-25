package com.qa.ims.persistence.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.qa.ims.persistence.domain.OrderDetail;
import com.qa.ims.utils.DBUtils;

public class OrderDetailsDAOTest {
        
    private final OrderDetailDAO DAO = new OrderDetailDAO();

	@BeforeAll
	public static void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema-orderdetail.sql", "src/test/resources/sql-data-orderdetail.sql");
	}

	@Test
	public void testCreate() {
		final OrderDetail created = new OrderDetail(2L, 1L, 2L, 10L);
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void testReadAll() {
		List<OrderDetail> expected = new ArrayList<>();
		expected.add(new OrderDetail(1L, 1L, 1L, 2L));
		assertEquals(expected, DAO.readAll());
	}

	@Test
	public void testReadLatest() {
		assertEquals(new OrderDetail(1L, 1L, 1L, 2L), DAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		assertEquals(new OrderDetail(1L, 1L, 1L, 2L), DAO.read(ID));
	}

	@Test
	public void testUpdate() {
		final OrderDetail updated = new OrderDetail(2L, 1L, 2L, 20L);
		assertEquals(updated, DAO.update(updated));
	}

	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}

}
