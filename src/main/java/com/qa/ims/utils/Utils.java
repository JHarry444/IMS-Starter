package com.qa.ims.utils;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;

public class Utils {

	private static final Logger LOGGER = LogManager.getLogger();
	private static final Logger INPUTS = LogManager.getLogger("inputlogger");

	private final Scanner scanner;

	public Utils(Scanner scanner) {
		super();
		this.scanner = scanner;
	}

	public Utils() {
		scanner = new Scanner(System.in);
	}

	public Long getLong() {
		String input = null;
		Long longInput = null;
		do {
			try {
				input = getString();
				if (input.equals("!")) {
					return -1l;
				}
				if (input.equals("*")) {
					return -2l;
				}
				longInput = Long.parseLong(input);
			} catch (NumberFormatException nfe) {
				LOGGER.info("Error - Please enter a number");
			}
		} while (longInput == null);
		return longInput;
	}

	public String getString() {
		INPUTS.log(Level.getLevel("INPUT"), "> ");
		String input = scanner.nextLine().strip().replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
		while (input.equals("")) {
			LOGGER.info("Error - Please enter a non-blank input");
		}
		return input;
	}

	public Double getDouble() {
		String input = null;
		Double doubleInput = null;
		do {
			try {
				input = getString();
				if (input.equals("!")) {
					return -1d;
				}
				if (input.equals("*")) {
					return -2d;
				}
				doubleInput = Double.parseDouble(input);
			} catch (NumberFormatException nfe) {
				LOGGER.info("Error - Please enter a number");
			}
		} while (doubleInput == null);
		return doubleInput;
	}

}
