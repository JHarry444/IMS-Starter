package com.qa.ims.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class OrderDetailTest {
        
    @Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(OrderDetail.class).withIgnoredFields("price").verify();
	}

	@Test
	public void testOrderDetail() {
		OrderDetail odetail = new OrderDetail(1L, 1L, 10L);
		assertNotNull(odetail);
		assertEquals(1L, odetail.getOrderID());
        assertEquals(1L, odetail.getItemID());
        assertEquals(10L, odetail.getQuantity());
	}

	@Test
	public void testOrderDetailWithID() {
		OrderDetail odetail = new OrderDetail(1L, 1L, 1L, 10L);
		assertNotNull(odetail);
        assertEquals(1L, odetail.getID());
		assertEquals(1L, odetail.getOrderID());
        assertEquals(1L, odetail.getItemID());
        assertEquals(10L, odetail.getQuantity());
	}

    @Test
	public void testOrderDetailWithPrice() {
		OrderDetail odetail = new OrderDetail(1L, 1L, 1L, 10L, 5.25D);
		assertNotNull(odetail);
        assertEquals(1L, odetail.getID());
		assertEquals(1L, odetail.getOrderID());
        assertEquals(1L, odetail.getItemID());
        assertEquals(10L, odetail.getQuantity());
        assertEquals(5.25D, odetail.getPrice());
	}

	@Test
	public void testGetID() {
		OrderDetail odetail = new OrderDetail(1L, 1L, 1L, 10L, 5.25D);
		assertEquals(1L, odetail.getID());
    }

    @Test
    public void testSetID() {
        OrderDetail odetail = new OrderDetail(1L, 1L, 1L, 10L, 5.25D);
        odetail.setID(2L);
        assertEquals(2L, odetail.getID());
    }

    @Test
	public void testGetOrderID() {
		OrderDetail odetail = new OrderDetail(1L, 1L, 1L, 10L, 5.25D);
		assertEquals(1L, odetail.getOrderID());
    }

    @Test
    public void testSetOrderID() {
        OrderDetail odetail = new OrderDetail(1L, 1L, 1L, 10L, 5.25D);
        odetail.setOrderID(2L);
        assertEquals(2L, odetail.getOrderID());
    }

    @Test
	public void testGetItemID() {
		OrderDetail odetail = new OrderDetail(1L, 1L, 1L, 10L, 5.25D);
		assertEquals(1L, odetail.getItemID());
    }

    @Test
    public void testSetItemID() {
        OrderDetail odetail = new OrderDetail(1L, 1L, 1L, 10L, 5.25D);
        odetail.setItemID(2L);
        assertEquals(2L, odetail.getItemID());
    }

    @Test
	public void testGetQuantity() {
		OrderDetail odetail = new OrderDetail(1L, 1L, 1L, 10L, 5.25D);
		assertEquals(10L, odetail.getQuantity());
    }

    @Test
    public void testSetQuantity() {
        OrderDetail odetail = new OrderDetail(1L, 1L, 1L, 10L, 5.25D);
        odetail.setQuantity(20L);
        assertEquals(20L, odetail.getQuantity());
    }

    @Test
    public void testGetString() {
        OrderDetail odetail = new OrderDetail(1L, 1L, 1L, 10L, 5.25D);
        assertEquals("id:" + odetail.getID() + " order ID:" + odetail.getOrderID() + " item ID:" + odetail.getItemID() + " quantity:" + odetail.getQuantity(), odetail.toString());
    }

    @Test
    public void testGetFormattedString() {
        OrderDetail odetail = new OrderDetail(1L, 1L, 1L, 10L, 5.25D);
        assertEquals("-> Item ID: " + odetail.getItemID() + ", Quantity: " + odetail.getQuantity() + ", Price: " + odetail.getFormattedPrice(), odetail.formattedString());
	}
}
