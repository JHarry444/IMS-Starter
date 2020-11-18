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
		Long item_id = resultSet.getLong("item_id");
		String item_name = resultSet.getString("item_name");
		Double item_price = resultSet.getDouble("item_price");
		return new Item(item_id, item_name, item_price);
	}
	
	/**
	 * Reads all Items from the database 
	 * 
	 * @return A list of Items 
	 */
	
	@Override
	public List<Item> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items");) {
			List<Item> items = new ArrayList<>();
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
	
	public Item readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items ORDER BY item_id DESC LIMIT 1");){
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * Creates an Item in the Database 
	 * 
	 * @param item - takes in an item object. id will be ignored
	 */

	@Override
	public Item create(Item item) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();){
			statement.executeUpdate("INSERT INTO items(item_name, item_price) values('" + item.getItem_name() 
			+ "','" + item.getItem_price() + "')");
		return readLatest(); 
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	public Item readItem(Long item_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultset = statement.executeQuery("SELECT * FROM items WHERE item_id = " + item_id);){
			resultset.next();
			return modelFromResultSet(resultset);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * Updates an Item in the Database 
	 * 
	 * @param Item - takes in an item object, the id field will be used to 
	 *               update that customer in the database 
	 */

	@Override
	public Item update(Item item) {
		try (Connection connection = DBUtils.getInstance().getConnection(); 
				Statement statement = connection.createStatement(); ) {
			statement.executeUpdate("update items set item_name = '" + item.getItem_name() + "', item_price = '"
					+ item.getItem_price() + "' where item_id =" + item.getItem_id());
			return readItem(item.getItem_id());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * Deletes an Item in the Database 
	 * 
	 * @param id - id of the Item 
	 */

	@Override
	public int delete(long item_id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();){
			return statement.executeUpdate("DELETE FROM items WHERE item_id = " + item_id);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}
	
	
}