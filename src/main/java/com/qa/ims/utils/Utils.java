package com.qa.ims.utils;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utilities class for reading in user input.
 * 
 * @author jh
 *
 */
public class Utils {

	private static Logger LOGGER = LogManager.getLogger();

	private final Scanner scanner;

	/**
	 * Initialises the class with an existing <code>Scanner</code> object.
	 * 
	 * @param scanner
	 * @see Scanner
	 */
	public Utils(Scanner scanner) {
		super();
		this.scanner = scanner;
	}

	/**
	 * Initialises the class with a new <code>Scanner</code> object.
	 * 
	 * @param scanner
	 * @see Scanner
	 */
	public Utils() {
		scanner = new Scanner(System.in);
	}

	/**
	 * Uses the <code>getDouble</code> method to get the user's input and then
	 * converts it to a <code>Long</code>.
	 * 
	 * @return User's input as a <code>Long</code>
	 */
	public Long getLong() {
		return this.getDouble().longValue();
	}

	/**
	 * Uses the <code>nextLine</code> method to read in a text input from the user.
	 * 
	 * @return user input as a <code>String</code>
	 */
	public String getString() {
		return scanner.nextLine();
	}

	/**
	 * Uses the <code>getString</code> method to get the user's input and then
	 * attempts to parse it to a <code>Double</code>. In the event of an invalid
	 * (non-numeric) input the user will be prompted to enter a valid number, this
	 * will continue until a valid input is received.
	 * 
	 * @return User's input as a <code>Double</code>
	 */
	public Double getDouble() {
		String input = null;
		Double doubleInput = null;
		do {
			try {
				input = getString();
				doubleInput = Double.parseDouble(input);
			} catch (NumberFormatException nfe) {
				LOGGER.info("Error - Please enter a number");
			}
		} while (doubleInput == null);
		return doubleInput;
	}

}
