package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAO implements Dao<Item> {

	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Item modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long productID = resultSet.getLong("product_id");
		String productName = resultSet.getString("product_name");
		String artistName = resultSet.getString("artist_name");
		Double releaseDate = resultSet.getDouble("release_date");
		Double productPrice = resultSet.getDouble("product_price");
		Double productQty = resultSet.getDouble("product_qty");
		return new Item(productID, productName, artistName, releaseDate, productPrice, productQty);
	}

	/**
	 * Reads all customers from the database
	 * 
	 * @return A list of customers
	 */
	@Override
	public List<Item> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from items");) {
			List<Item> item = new ArrayList<>();
			while (resultSet.next()) {
				item.add(modelFromResultSet(resultSet));
			}
			return item;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Item readLatest() {
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
	 * Creates a customer in the database
	 * 
	 * @param customer - takes in a customer object. id will be ignored
	 */
	@Override
	public Item create(Item item) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO items(product_name, artist_name, release_date, product_price, product_qty) values('" + item.getProductName()
					+ "','" + item.getArtistName() + "','" + item.getReleaseDate() + "','" + item.getProductPrice() + "','" + item.getProductQty() + "')");
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Item readItem(Long productID) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items where product_id = " + productID);) {
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
	public Item update(Item item) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("update items set product_name ='" + item.getProductName() + "', artist_name ='"
					+ item.getArtistName() + "' release_date =" + item.getReleaseDate()
					 + "' product_price =" + item.getProductPrice() + "'product_qty =" + item.getProductQty() + "'where product_id =" + item.getProductID());
			return readItem(item.getProductID());
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
	public int delete(long productID) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			return statement.executeUpdate("delete from items where product_id = " + productID);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

}
