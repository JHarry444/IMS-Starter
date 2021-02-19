package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.qa.ims.persistence.domain.OrderDetail;
import com.qa.ims.utils.DBUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrderDetailDAO implements Dao<OrderDetail> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public OrderDetail modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		Long orderID = resultSet.getLong("orderid");
		Long itemID = resultSet.getLong("itemid");
        Double quantity = resultSet.getDouble("quantity");
		return new OrderDetail(id, orderID, itemID, quantity);
	}

	/**
	 * Reads all orderdetails from the database
	 * 
	 * @return A list of orderdetails
	 */
	@Override
	public List<OrderDetail> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orderdetails");) {
			List<OrderDetail> orderdetails = new ArrayList<>();
			while (resultSet.next()) {
				orderdetails.add(modelFromResultSet(resultSet));
			}
			return orderdetails;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public OrderDetail readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orderdetails ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates a orderdetail in the database
	 * 
	 * @param orderdetail - takes in a orderdetail object. id will be ignored
	 */
	@Override
	public OrderDetail create(OrderDetail orderdetail) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO orderdetails(orderid, itemid, quantity) VALUES (?, ?, ?)");) {
			statement.setLong(1, orderdetail.getOrderID());
			statement.setLong(2, orderdetail.getItemID());
			statement.setDouble(3, orderdetail.getQuantity());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public OrderDetail read(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM orderdetails WHERE id = ?");) {
			statement.setLong(1, id);
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

	public List<OrderDetail> readOrder(Long orderID) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM orderdetails where orderid = ?");) {
			statement.setLong(1, orderID);
			try (ResultSet resultSet = statement.executeQuery();) {
				List<OrderDetail> orderdetails = new ArrayList<>();
				while (resultSet.next()) {
					orderdetails.add(modelFromResultSet(resultSet));
				}
				return orderdetails;
			}			
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates a orderdetail in the database
	 * 
	 * @param orderdetail - takes in a orderdetail object, the id field will be used to
	 *                 update that orderdetail in the database
	 * @return
	 */
	@Override
	public OrderDetail update(OrderDetail orderdetail) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE orderdetails SET orderid = ?, itemid = ?, quantity = ? WHERE id = ?");) {
			statement.setLong(1, orderdetail.getOrderID());
			statement.setLong(2, orderdetail.getItemID());
			statement.setDouble(3, orderdetail.getQuantity());
			statement.setLong(4, orderdetail.getID());
			statement.executeUpdate();
			return read(orderdetail.getID());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Deletes a orderdetail in the database
	 * 
	 * @param id - id of the orderdetail
	 */
	@Override
	public int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM orderdetails WHERE id = ?");) {
			statement.setLong(1, id);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

}
