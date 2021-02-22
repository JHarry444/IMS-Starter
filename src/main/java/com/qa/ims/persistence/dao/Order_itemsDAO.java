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

import com.qa.ims.persistence.domain.Order_items;
import com.qa.ims.utils.DBUtils;

public class Order_itemsDAO {

	public static final Logger LOGGER = LogManager.getLogger();

	public Order_items modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long order_items_id = resultSet.getLong("order_items_id");
		Long item_id = resultSet.getLong("item_id");
		int quantity = resultSet.getInt("quantity");
		Long order_id = resultSet.getLong("order_id");
		return new Order_items(order_items_id, item_id, quantity, order_id);
	}

	public Order_items readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT * FROM order_items ORDER BY order_items_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	// Adding items to the order
	public Order_items create(Order_items order_items) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO order_items(item_id, quantity, order_id) VALUES (?, ?, ?)");) {
			statement.setLong(1, order_items.getItem_id());
			statement.setInt(2, order_items.getQuantity());
			statement.setLong(3, order_items.getOrder_id());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Order_items read(Long order_items_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM order_items WHERE order_items_id = ?");) {
			statement.setLong(1, order_items_id);
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

	public Order_items update(Order_items order_items) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"UPDATE order_items set item_id = ? , quantity  = ?, order_id = ? where order_items_id = ?");) {
			statement.setLong(1, order_items.getItem_id());
			statement.setInt(2, order_items.getQuantity());
			statement.setLong(3, order_items.getOrder_id());
			statement.executeUpdate();
			return read(order_items.getOrder_items_id());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public int delete(long item_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM order_items WHERE item_id = ?");) {
			statement.setLong(1, item_id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

	public List<Order_items> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM order_items");) {
			List<Order_items> order_items = new ArrayList<>();
			while (resultSet.next()) {
				order_items.add(modelFromResultSet(resultSet));
			}
			return order_items;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}
}
