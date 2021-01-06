package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.DBUtils;

public class OrdersDAO implements Dao<Orders> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Orders modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long orderID = resultSet.getLong("order_id");
		Long customerID = resultSet.getLong("customer_id");
		Long productID = resultSet.getLong("product_id");
		Double orderQty = resultSet.getDouble("order_qty");
		Double totalPrice = resultSet.getDouble("total_price");
		Boolean orderStatus = resultSet.getBoolean("order_status");
		return new Orders(orderID, customerID, productID, orderQty, totalPrice, orderStatus);
	}

	/**
	 * Reads all customers from the database
	 * 
	 * @return A list of customers
	 */
	@Override
	public List<Orders> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from orders");) {
			List<Orders> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(modelFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Orders readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY product_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates a customer in the database
	 * 
	 * @param customer - takes in a customer object. id will be ignored
	 */
	@Override
	public Orders create(Orders orders) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO orders(customer_id, product_id, order_qty, total_price, order_status) values('" + orders.getCustomerID()
					+ "','" + orders.getProductID() + "','" + orders.getOrderQty() + "','" + orders.getTotalPrice() + "','" + orders.getOrderStatus() + "')");
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Orders readOrders(Long orderID) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders where order_id = " + orderID);) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates a customer in the database
	 * 
	 * @param customer - takes in a customer object, the id field will be used to
	 *                 update that customer in the database
	 * @return
	 */
	@Override
	public Orders update(Orders orders) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("update orders set customer_id ='" + orders.getCustomerID() + "', product_id ='"
					+ orders.getProductID() + "' order_qty =" + orders.getOrderQty()
					 + "' total_price =" + orders.getTotalPrice() + "'order_status =" + orders.getOrderStatus() + "'where order_id =" + orders.getOrderID());
			return readOrders(orders.getOrderID());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Deletes a customer in the database
	 * 
	 * @param id - id of the customer
	 */
	@Override
	public int delete(long orderID) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			return statement.executeUpdate("delete from orders where order_id = " + orderID);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

}