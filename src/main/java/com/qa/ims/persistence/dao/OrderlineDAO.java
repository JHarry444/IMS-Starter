package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Orderline;
import com.qa.ims.utils.DBUtils;

public class OrderlineDAO implements Dao<Orderline> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Orderline modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long orderlineID = resultSet.getLong("orderline_id");
		Long orderID = resultSet.getLong("order_id");
		Long productID = resultSet.getLong("product_id");
		return new Orderline(orderlineID, orderID, productID);
	}

	/**
	 * Reads all customers from the database
	 * 
	 * @return A list of customers
	 */
	@Override
	public List<Orderline> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from orderline");) {
			List<Orderline> orderline = new ArrayList<>();
			while (resultSet.next()) {
				orderline.add(modelFromResultSet(resultSet));
			}
			return orderline;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Orderline readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orderline ORDER BY orderline_id DESC LIMIT 1");) {
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
	public Orderline create(Orderline orderline) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO orderline(order_id, product_id) values('" + orderline.getOrderID()
					+ "','" + orderline.getProductID());
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Orderline readOrders(Long orderlineID) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orderline where orderline_id = " + orderlineID);) {
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
	public Orderline update(Orderline orderline) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("update orderline set order_id ='" + orderline.getOrderID() + "', product_id ='"
					+ orderline.getProductID() + "'where orderline_id =" + orderline.getOrderlineID());
			return readOrderline(orderline.getOrderlineID());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

		private Orderline readOrderline(Long orderlineID) {
		return null;
	}

	/**
	 * Deletes a customer in the database
	 * 
	 * @param id - id of the customer
	 */
	@Override
	public int delete(long orderlineID) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			return statement.executeUpdate("delete from orders where orderline_id = " + orderlineID);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

}