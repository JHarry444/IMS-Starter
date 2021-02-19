package com.qa.ims;

import java.sql.SQLException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner {

	public static final Logger LOGGER = LogManager.getLogger();
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		IMS ims = new IMS();
		ims.imsSystem();
		LOGGER.info("SO LONG!");

	}
}