package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.controller.OrderController;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order> {
public static final Logger LOGGER = LogManager.getLogger();


public String convertMapToString(Map<Long, Long> itemmap) {
	StringBuilder mapToString = new StringBuilder();
	for (Long key:itemmap.keySet()) {
		mapToString.append(key + "=" + itemmap.get(key) + ", ");
	}
	mapToString.delete(mapToString.length()-2, mapToString.length()).append("}");
	return mapToString.toString();	
}

	
	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		Long orderid = resultSet.getLong("orderid");
		return new Order (id, orderid);
	}
	
	
	@Override
	public List<Order> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders INNER JOIN orderline ON orders.orderid = orderline.orderid;");) {
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
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY orderid DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Order create(Order orders) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("INSERT INTO orders(id) VALUES (?);");) {

				statement.setLong(1, orders.getId());
				statement.executeUpdate();
				return readLatest();
			}
			catch (Exception e) {
				LOGGER.debug(e);
				LOGGER.error(e.getMessage());
			}

			return null;
	}
	
	@Override
	public Order read(Long orderid) {
		return null;
	}

	@Override
	public Order update(Order orders) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("INSERT INTO orders(id) VALUES (?);");) {

				statement.setLong(1, orders.getId());
				statement.executeUpdate();
				return readLatest();
			}
			catch (Exception e) {
				LOGGER.debug(e);
				LOGGER.error(e.getMessage());
			}

			return null;
	}

	@Override
	public int delete(long orderid) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE orderid  = ?; DELETE FROM orderline WHERE orderid = ?;");) {
			statement.setLong(1, orderid);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}



}
