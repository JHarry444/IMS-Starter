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
		Long order_item_id = resultSet.getLong("order_item_id");
		Double unit_price = resultSet.getDouble("unit_price");
		int quantity = resultSet.getInt("quantity");
//		Long item_id = resultSet.getLong("item_id");

		
		return new Orders(order_id, order_item_id, unit_price, quantity);	
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
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY id DESC LIMIT 1");){
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
			statement.executeUpdate("INSERT INTO orders_items (fk_item_id) values ("
					+ order.getItem().getItem_id() + ")");
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;	
	}
	
	// read take in order_id and read all items in the orders_items table that have that order id 

	@Override
	public Orders update(Orders order) {
////		try (Connection connection = DBUtils.getInstance().getConnection();
////				Statement statement = connection.createStatement();){
////			statement.executeUpdate("UPDATE orders set order_id = '" + order.getOrder_id());
////			
////			return readOrder(order.getOrder_id());
//		}
		
		return null;
	}


	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}



}
