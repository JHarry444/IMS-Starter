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
		
		Long order_id = resultSet.getLong("fk_order_id");
		int item_id = resultSet.getInt("fk_item_id");
		Long order_item_id = resultSet.getLong("order_item_id");
		Double unit_price = resultSet.getDouble("unit_price");
		int quantity = resultSet.getInt("quantity");


		
		return new Orders(order_item_id, unit_price, quantity,order_id, item_id);	
	}	
	
	public Orders modelFromResultSet2(ResultSet resultSet) throws SQLException {
		
		Long order_id = resultSet.getLong("order_id");
		Long customer_id = resultSet.getLong("fk_customer_id");
		
		return new Orders(order_id, customer_id);	
	}
	
	
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
				order.add(modelFromResultSet2(resultSet));
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
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders_items ");){
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
			ResultSet resultSet = statement.executeQuery("SELECT order_id FROM orders WHERE fk_customer_id = " + order.getCustomer().getId());
			while(resultSet.next()) {
				LOGGER.info("Your order ID is:" + resultSet.getLong("order_id"));
			}
			return order;
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	// Create method -  for creating something in the orders_items table 
	public Orders createOrdersItems(Orders orderItem) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();){
			statement.executeUpdate("INSERT INTO orders_items (fk_item_id, fk_order_id, quantity, unit_price) values ("
					+ orderItem.getItem().getItem_id()+ "," + orderItem.getOrder_id()+ "," + orderItem.getQuantity()+ "," + orderItem.getUnit_price() + ")");
			ResultSet resultSet = statement.executeQuery("SELECT item_price FROM items WHERE item_id = " + orderItem.getItem_id());
			while(resultSet.next()) {
				Double totalPrice = resultSet.getDouble("item_price");
				orderItem.setUnit_price(totalPrice * orderItem.getQuantity());
				LOGGER.info("Total Price = " + orderItem.getUnit_price());
				statement.executeUpdate("UPDATE orders_items SET unit_price = " + orderItem.getUnit_price() + " WHERE fk_order_id = " + orderItem.getOrder_id() + " AND fk_item_id = " + orderItem.getItem_id());
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;	
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

	@Override
	public Orders update(Orders t) {
		// TODO Auto-generated method stub
		return null;
	}


}
