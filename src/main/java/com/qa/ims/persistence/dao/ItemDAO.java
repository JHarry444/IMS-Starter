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

public class ItemDAO {
	public static final Logger LOGGER = LogManager.getLogger();

	public Item modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("ItemID");
		String title = resultSet.getString("title");
		Double price = resultSet.getDouble("price");
		return new Item(id,title,price);
	}
	//reading items
	public List<Item> readAll(){
		try(Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from items");
				) {
					List<Item> items = new ArrayList<>();
					while(resultSet.next()) {
						items.add(modelFromResultSet(resultSet));
					}
					return items;
				}catch (SQLException e) {
					LOGGER.debug(e);
					LOGGER.error(e.getMessage());
				}
				return new ArrayList<>();
	}
	public Item readLatest() {
		try(Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("Select * from items order by ItemID desc limit 1");
				){
					resultSet.next();
					return modelFromResultSet(resultSet);
				} catch(Exception e) {
					LOGGER.debug(e);
					LOGGER.error(e.getMessage());
				}
				return null;
	}
	public Item create(Item item) {
		try(
			Connection connection = DBUtils.getInstance().getConnection();
			Statement statement = connection.createStatement();
			){
			statement.executeUpdate("insert into items(title,price)values('"+item.getTitle()+"','"+item.getValue()+"')");
			return readLatest();
	}	catch(Exception e) {
		LOGGER.debug(e);
		LOGGER.error(e.getMessage());
	}
	return null;
	}
	
	public Item readItem(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items where ItemID = " + id);) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	public Item update(Item item) {
		try(
			Connection connection = DBUtils.getInstance().getConnection();
			Statement statement = connection.createStatement();)
		{
			statement.executeUpdate("update items set title='"+item.getTitle()+"',price ="+item.getValue()+"WHERE ItemID ="+item.getID());
			return readItem(item.getID());
		} catch(Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	public static int delete(long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
				return statement.executeUpdate("delete from items where ItemID ="+id+";");
		}catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
				
	}
	
	
	
	
	
	
	
	
}
