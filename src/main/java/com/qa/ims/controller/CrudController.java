package com.qa.ims.controller;

import java.util.List;

import com.qa.ims.persistence.domain.OrderItems;

/**
 * Create, Read, Update and Delete controller. Takes in inputs for each action
 * to be sent to a service class
 */
public interface CrudController<T> {

	List<T> readAll();

	T create();

	T update();

	int delete();


}
