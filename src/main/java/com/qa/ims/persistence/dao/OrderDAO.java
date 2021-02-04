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

public class OrderDAO implements Dao<Order> {
	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT * FROM orders O JOIN order_item OI ON O.order_id = OI.order_id");) {
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

	@Override
	public Order read(Long order_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE item_id = ?");) {
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

	@Override
	public Order create(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();

				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orders (id, order_price) VALUES(?,?)");) {
			statement.setLong(1, order.getCustomer_id());
			statement.setLong(2, 0);
			statement.executeUpdate();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY order_id DESC LIMIT 1");
			while (resultSet.next()) {
				order.setOrder_id(resultSet.getInt("order_id"));
			}
			statement.executeUpdate("INSERT INTO order_item (order_id, item_id) VALUES (" + order.getOrder_id() + ","
					+ order.getItem_id() + ")");
			{
			}
			ResultSet resultPrice = statement.executeQuery("SELECT * FROM items WHERE item_id =" + order.getItem_id());
			while (resultPrice.next()) {
				order.setOrder_price(resultPrice.getInt("item_price"));
			}
			statement.executeUpdate("Update orders SET order_price = (order_price + " + order.getOrder_price()
					+ ") WHERE order_id = " + order.getOrder_id());
			{

			}
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}

		return null;
	}

	private Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT * FROM orders O JOIN order_item OI ON O.order_id=OI.order_id");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Order update(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO order_item (order_id,item_id) VALUES (" + order.getOrder_id()
								+ "," + order.getItem_id() + ")");) {
			statement.executeUpdate();
			ResultSet resultPrice = statement.executeQuery("SELECT * FROM items WHERE item_id =" + order.getItem_id());
			while (resultPrice.next()) {
				order.setOrder_price(resultPrice.getInt("item_price"));
			}
			statement.executeUpdate("Update orders SET order_price = (order_price + " + order.getOrder_price()
					+ ") WHERE order_id = " + order.getOrder_id());
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
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

	public int deleteItem(long order_id, long item_id) {
		long item_price = 0;
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM order_item WHERE order_id = ? && item_id = ?");) {
			statement.setLong(1, order_id);
			statement.setLong(2, item_id);
			statement.executeUpdate();
			ResultSet resultPrice = statement.executeQuery("SELECT * FROM items WHERE item_id = " + item_id);
			while (resultPrice.next()) {
				item_price = (resultPrice.getInt("item_price"));
			}
			statement.executeUpdate(
					"Update orders SET order_price = (order_price - " + item_price + ") WHERE order_id = " + order_id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long order_id = resultSet.getLong("order_id");
		Long customer_id = resultSet.getLong("id");
		Long item_id = resultSet.getLong("item_id");
		Long order_item_id = resultSet.getLong("order_item_id");
		return new Order(order_id, item_id, customer_id, order_item_id);
	}

}
