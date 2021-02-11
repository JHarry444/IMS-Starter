package com.qa.ims.ims;

import org.mockito.Mock;


import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.utils.DBUtils;
import com.qa.ims.utils.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.qa.ims.IMS;
import com.qa.ims.controller.CustomerController;
import com.qa.ims.controller.ItemController;
import com.qa.ims.controller.OrderController;
import com.qa.ims.ims.*;
import com.qa.ims.persistence.domain.Domain;

@RunWith(MockitoJUnitRunner.class)
public class ImsTest {
	@Mock
	private Utils utils;
	@Mock
	private CustomerController customercontroller;
	@Mock
	private OrderController orders;
	@Mock
	private ItemController items;
	@InjectMocks
	private IMS ims;
	@Test
	public void test() {
	}
}
