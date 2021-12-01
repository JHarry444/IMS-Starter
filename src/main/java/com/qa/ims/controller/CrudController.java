package com.qa.ims.controller;

import java.util.List;

/**
 * Create, Read, Update and Delete controller. Takes in inputs for each action
 * to be sent to a service class
 */
public interface CrudController<T> {

	List<T> readAll();

	T read();

	T readByName();

	T create();

	T update();

	int delete();

}
