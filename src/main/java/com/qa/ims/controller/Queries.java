package com.qa.ims.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public enum Queries {
	//Universal READ ALL
	READALL("SELECT * FROM "),
	//SQL Queries for Orders and OrderItems
	READALLORDERITEMS("SELECT *, SUM(item_cost * quantity) as TotalCost,"
			+ " i.item_desc from orders_items oi"
			+ " left join items i on i.item_id=oi.item_id"
			+ " left join orders o on o.order_id=oi.order_id"
			+ " left join customers c on c.id=o.cust_id"
			+ " group by oi.order_item_id;"),
	READLATESTORDERITEM("SELECT * FROM orders_items ORDER BY order_id DESC LIMIT 1"),
	READLATESTORDER("SELECT * FROM orders ORDER BY order_id DESC LIMIT 1"),
	READORDERITEM("SELECT * FROM orders_items WHERE order_id = ?"),
	READORDER("SELECT * FROM orders WHERE order_id = ?"),
	READSPECIFICORDERITEM("SELECT c.first_name, c.surname, oi.order_id, oi.item_id, oi.quantity, i.item_name, i.item_cost, SUM(item_cost * quantity) as TotalCost\r\n"
			+ "								, i.item_desc from orders_items oi left join items i on i.item_id=oi.item_id left join orders o on o.order_id=oi.order_id left join customers\r\n"
			+ "								c on c.id=o.cust_id group by oi.order_item_id HAVING oi.order_id = ?;"),
	CREATEORDER("INSERT INTO orders (cust_id) VALUES (?)"),
	CREATEORDERITEM("INSERT INTO orders_items(item_id, order_id, quantity) VALUES (?, ?, ?)"),
	UPDATEORDER("UPDATE orders SET cust_id WHERE order_id = ?"),
	UPDATEORDERITEM("UPDATE orders_items SET quantity = ? WHERE order_id = ? AND item_id = ? "),
	DELETEORDER("DELETE o, oi FROM orders_items oi LEFT JOIN orders o on o.order_id=oi.order_id WHERE oi.order_id = ?;"),
	DELETEORDERITEM("DELETE oi FROM orders_items oi WHERE order_id = ? AND item_id = ?;"),
	DELETENULLORDERS("DELETE o from orders left join orders_items oi on o.order_id=oi.order_id where quantity is null;"),
	//SQL Queries for customer table.
	READLATESTCUSTOMER("SELECT * FROM customers ORDER BY id DESC LIMIT 1"),
	CREATECUSTOMER("INSERT INTO customers(first_name, surname) VALUES (?, ?)"),
	READCUSTOMER("SELECT * FROM customers WHERE id = ?"),
	UPDATECUSTOMER("UPDATE customers SET first_name = ?, surname = ? WHERE id = ?"),
	DELETECUSTOMER("DELETE FROM customers WHERE id = ?"),
	//SQL Queries for item table
	READLATESTITEM("SELECT * FROM items ORDER BY item_id DESC LIMIT 1"),
	CREATEITEM("INSERT INTO items (item_name, item_cost, item_desc) VALUES (?, ?, ?)"),
	READITEM("SELECT * FROM items WHERE item_id = ?"),
	UPDATEITEM("UPDATE items SET item_name = ?, item_cost = ?, item_desc = ? WHERE item_id = ?"),
	DELETEITEM("DELETE FROM items WHERE item_id = ?")
	
	;

	public static final Logger LOGGER = LogManager.getLogger();
	
	private String description;
	
	private Queries(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
	
	public String specifyTable(String table) {
		return this.description + table;
	}


}
