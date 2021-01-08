package com.qa.ims.controller;

import java.util.List;

import com.qa.ims.persistence.domain.Item;

/**
 * Create, Read, Update and Delete controller. Takes in inputs for each action
 * to be sent to a service class
 */
public interface CrudController<T> {

	List<T> readAll();

	T create();

	T update();

	int delete();

	/**
	 * Creates a customer by taking in user input
	 * @param itemPrice 
	 */
	Item create(Object itemPrice);

	/**
	 * Creates a customer by taking in user input
	 * @param itemPrice 
	 * @param itemName 
	 * @param itemID 
	 */
	Item create(Object itemPrice, Class<Double> itemName, String itemID);

	/**
	 * Updates an existing customer by taking in user input
	 * @param itemPrice 
	 */
	Item update(Class<Double> itemPrice);

}
