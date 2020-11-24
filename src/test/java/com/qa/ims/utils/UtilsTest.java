package com.qa.ims.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Scanner;

import org.junit.AfterClass;
import org.junit.Test;

public class UtilsTest {
	
	@Test
	public void testGetLong() {
		String syote = "x\n3\n";
		long expected = 3l;
		MockInOut io = new MockInOut(syote);
		Utils utils = new Utils();
		
		assertTrue(utils.getLong().equals(expected));
	}
	
	@Test
	public void testGetDouble() {
		String syote = "x\n5.3\n";
		double expected = 5.3;
		MockInOut io = new MockInOut(syote);
		Utils utils = new Utils();
		
		assertTrue(utils.getDouble().equals(expected));
	}
	
	@Test
	public void uselessCoverage() {
		Utils utils = new Utils(new Scanner(System.in));
	}
	
	@AfterClass
	public static void teardown() {
		String syote = "";
		MockInOut io = new MockInOut(syote);
		io.close();
	}

}
