package com.qa.ims;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.CustomerController;
import com.qa.ims.controller.ItemController;
import com.qa.ims.controller.OrderController;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class imsTest {
	
	@Mock
	private CustomerController customers;
	
	@Mock
	private ItemController items;
	
	@Mock
	private OrderController orders;
	
	@Mock
	private Utils utils;
	
	@InjectMocks
	private IMS ims;
	
	@Test
	public void systemTest() {
		when(utils.getString()).thenReturn("root","root")
		.thenReturn("CUSTOMER","create","read","update","delete","return")
		.thenReturn("ITEM","create","read","update","delete","return")
		.thenReturn("ORDER","create","read","update","delete","return","stop");
		when(customers.create()).thenReturn(null);
		when(customers.readAll()).thenReturn(null);
		when(customers.update()).thenReturn(null);
		when(customers.delete()).thenReturn(0);
		when(items.create()).thenReturn(null);
		when(items.readAll()).thenReturn(null);
		when(items.update()).thenReturn(null);
		when(items.delete()).thenReturn(0);
		when(orders.create()).thenReturn(null);
		when(orders.readAll()).thenReturn(null);
		when(orders.update()).thenReturn(null);
		when(orders.delete()).thenReturn(0);
		
		ims.imsSystem();
		
		verify(customers, times(1)).create();
		verify(customers, times(1)).readAll();
		verify(customers, times(1)).update();
		verify(customers, times(1)).delete();
		verify(items, times(1)).create();
		verify(items, times(1)).readAll();
		verify(items, times(1)).update();
		verify(items, times(1)).delete();
		verify(orders, times(1)).create();
		verify(orders, times(1)).readAll();
		verify(orders, times(1)).update();
		verify(orders, times(1)).delete();
	}
	
	@Test
	public void uselessTest() {
		IMS testims = new IMS();
		assertNotNull(testims);
	}
}
