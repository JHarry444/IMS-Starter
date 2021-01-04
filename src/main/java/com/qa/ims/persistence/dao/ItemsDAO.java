package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.sql.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.DBUtils;

public class ItemsDAO implements Dao<Items> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Items modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long productID = resultSet.getLong("product_id");
		String productName = resultSet.getString("product_name");
		String artist = resultSet.getString("artist");
		Date releaseDate = resultSet.getDate("release_date");
		BigDecimal productPrice = resultSet.getBigDecimal("product_price");
		Double productQty = resultSet.getDouble("product_qty");
		return new Items(productID, productName, artist, releaseDate, productPrice, productQty);
	}

	/**
	 * Reads all customers from the database
	 * 
	 * @return A list of customers
	 */
	@Override
	public List<Items> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from items");) {
			List<Items> items = new ArrayList<>();
			while (resultSet.next()) {
				items.add(modelFromResultSet(resultSet));
			}
			return items;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Items readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items ORDER BY product_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates an Item in the database
	 * 
	 * @param item - takes in a item object. id will be ignored
	 */
	@Override
	public Items create(Items items) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO items(product_name, artist, release_date, product_price, product_qty) values('" + items.getProductName()
					+ "','" + items.getArtist() + "','" + items.getReleaseDate() + "','" + items.getProductPrice() + "','" + items.getProductQty() + "')");
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Items readItems(Long product_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items where product id = " + product_id);) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates an item in the database
	 * 
	 * @param item - takes in an item object, the id field will be used to
	 *                 update that item in the database
	 * @return
	 */
	@Override
	public Items update(Items items) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("update item set product_name ='" + items.getProductName() + "', artist ='"
					+ items.getArtist() + "', release_date ='"
							+ items.getReleaseDate() + "', product_price ='"
									+ items.getProductPrice() + "', product_qty ='"
											+ items.getProductQty() + "' where product_id =" + items.getProductID());
			return readItems(items.getProductID());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Deletes an item in the database
	 * 
	 * @param id - id of the item
	 */
	@Override
	public int delete(long ProductID) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			return statement.executeUpdate("delete from items where product_id = " + ProductID);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

}
