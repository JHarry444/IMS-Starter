package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.DBUtils;

public class OrdersDAO implements Dao<Orders> {
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	@Override
	public Orders modelFromResultSet(ResultSet resultSet) throws SQLException {
		
		Long order_id = resultSet.getLong("order_id");
		Long customer_id = resultSet.getLong("fk_customer_id");


		
		return new Orders(order_id, customer_id);	
	}	
	
	// model result for return items 
	
	
	/**
	 * Reads all Orders from the database
	 * 
	 * @return A list of customers
	 */

	@Override
	public List<Orders> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");){
			List<Orders> order = new ArrayList<>();
			while (resultSet.next()) {
				order.add(modelFromResultSet(resultSet));
			}
			return order;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}
	
	public Orders readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY order_id DESC LIMIT 1");){
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Orders create(Orders order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();){
			statement.executeUpdate("INSERT INTO orders(fk_customer_id) values (" + 
				order.getCustomer().getId() +") " );
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	// Create method -  for creating something in the orders_items table 
	public Orders createOrdersItems(Orders order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();){
			//TotalCost(order); calling the method 
			statement.executeUpdate("INSERT INTO orders_items (fk_item_id) values ("
					+ order.getItem().getItem_id() + ")"); // Add query for total cost 
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;	
	}
	
	// read take in order_id and read all items in the orders_items table that have that order id 

	@Override
	public Orders update(Orders order) {
//		try (Connection connection = DBUtils.getInstance().getConnection();
//				Statement statement = connection.createStatement();){
//			statement.executeUpdate("UPDATE orders set order_id = '" + order.getOrder_id());
//			
//			return readOrder(order.getOrder_id());
//		}
		
		return null;
	}
	
	/**
	 * Method to Calculate the total cost of the order ( unit_price ) 
	 * This will take Order by order_id and then take the items by item_id
	 * Then Multiply price of items (item_price) by quantity (quantity)
	 * use getters and setters 
	 * 
	 * set the total for the total items 
	 */
	

	public void TotalCost (Orders order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			String order_idQuery = "SELECT item_price FROM items WHERE fk_customer_id= " + order.getCustomer().getId();
			ResultSet ordersQuery = statement.executeQuery(order_idQuery);
			
			while(ordersQuery.next()) {
				order.setOrder_id(ordersQuery.getLong("order_id"));
			}
			
			String itemQuery = "SELECT item_price FROM items WHERE item_id = " + order.getItem_id();
			ResultSet itemPrice= statement.executeQuery(itemQuery);
			
			while(itemPrice.next()) {
				order.setUnit_price(itemPrice.getDouble("item_price"));
			}
			
			double orderCost = order.getUnit_price() * order.getQuantity();
			order.setUnit_price(orderCost);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public int delete(long order_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();){
			return statement.executeUpdate("DELETE FROM orders WHERE order_id = " + order_id);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
	
		return 0;
	}


}
