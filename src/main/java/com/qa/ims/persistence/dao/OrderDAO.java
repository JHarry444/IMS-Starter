package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAO {

	public static final Logger LOGGER = LogManager.getLogger();

	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long order_id = resultSet.getLong("order_id");
		int cust_id = resultSet.getInt("cust_id");
		Long item_id = resultSet.getLong("item_id");
		int quantity = resultSet.getInt("quantity");
		float total_cost = resultSet.getFloat("total_cost");
		return new Order(order_id, cust_id, item_id, quantity, total_cost);

	}

	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select *, round((items.value * orders.quantity),2) as \"total_cost\"\r\n"
						+ "from customers, items, orders\r\n"
						+ "where orders.item_id = items.item_id\r\n"
						+ "and orders.cust_id =  customers.id");) {
			List<Order> orders = new ArrayList<>();
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

	public Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY order_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Order create(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orders(cust_id, item_id, quantity) VALUES (?,?,?)");) {
			statement.setLong(1, order.getCust_id());
			statement.setFloat(2, order.getItem_id());
			statement.setInt(3, order.getQuantity());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Order read(Long order_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE order_id = ?");) {
			statement.setLong(1, order_id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Order update(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE orders SET item_id = ?, quantity = ? WHERE order_id = ?");) {
			statement.setLong(1, order.getItem_id());
			statement.setInt(2, order.getQuantity());
			statement.setLong(3, order.getOrder_id());
			statement.executeUpdate();
			return read(order.getOrder_id());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public int delete(long order_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE order_id = ?");) {
			statement.setLong(1, order_id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

}
