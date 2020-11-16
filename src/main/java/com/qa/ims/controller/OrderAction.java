package com.qa.ims.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.utils.Utils;


public enum OrderAction {
	ADD("Add an item to an order"),REMOVE("Remove an item from an order"),
	CUSTOMER("Change the customer of an order"),RETURN("Do nothing and return");
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	private String description;
	
	OrderAction(String description){
		this.description = description;
	}
	public String getDescription() {
		return this.name() + ": " + this.description;
	}
	
	public static void printActions() {
		for (OrderAction action : OrderAction.values()) {
			LOGGER.info(action.getDescription());
		}
	}
	
	public static OrderAction getAction(Utils utils) {
		OrderAction orderaction = null;
		do {
			try {orderaction = OrderAction.valueOf(utils.getString().toUpperCase());
			}catch(IllegalArgumentException e) {
				LOGGER.info("Invalid selection please try again");
			}
		} while (orderaction == null);
		return orderaction;
	}
}
